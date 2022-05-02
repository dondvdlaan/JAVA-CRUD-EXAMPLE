package de.dlaan.dlmietwagenverwaltung;

import de.dlaan.dlmietwagenverwaltung.assistance.PKWAssistance;
import de.dlaan.dlmietwagenverwaltung.logic.db.DbManager;
import de.dlaan.dlmietwagenverwaltung.listview.ListViewCellFactory;
import de.dlaan.dlmietwagenverwaltung.model.PKW;
import de.dlaan.dlmietwagenverwaltung.settings.AppCommands;
import de.dlaan.dlmietwagenverwaltung.settings.AppTexts;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static de.dlaan.dlmietwagenverwaltung.settings.AppCommands.*;
import static de.dlaan.dlmietwagenverwaltung.settings.AppTexts.*;

/**
 * Dieser Gui Kontroller kommuniziert mit dem View-Layout
 */
public class GuiController implements Initializable {

    // Interne Klasse fuer Warnmeldungen
    private class PopUpWarnings extends Alert{
        // Konstuktor
        PopUpWarnings(){
            // In Konstruktor Alert Klasse wird Alerttype gesetzt
            super(AlertType.INFORMATION);
        }

        /**
         * Meldung wird konfiguriert und mit empfangenem Text ausgegeben
         * @param strUserMessage : String : Text kommt aus dem Programm
         */
        void popUpMessage(String strUserMessage ){
            this.setTitle(MESSAGE_PAY_ATTENTION);
            this.setContentText(strUserMessage);
            this.show();
        }
    }

    // Region 0. Konstanten
    private static final boolean NOT_EDIBLE = true;
    private static final boolean EDIBLE = false;
    private static final boolean VISIBLE = true;
    private static final boolean NOT_VISIBLE = false;
    private static final String NOT_AVAILABLE = "false";
    private static final String AVAILABLE = "true";
    public static final char OLD_CHAR = ',';
    public static final char NEW_CHAR = '.';

    //endregion

    // Region 1. Variabelen und Attributen
    // Meldungen
    private PopUpWarnings popUpWarnings;

    // Zugangskontrolle
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField pwPassword;
    @FXML
    private Label lblLogInNote;
    @FXML
    private Label lblLogInNote_2;

    //ListView
    @FXML
    private ListView<PKW> lvDisplay;
    private ListViewCellFactory listViewCellFactory;
    private List<PKW> pkwList;
    private PKW currentSelectedPKW;

    // Aendern
    @FXML
    private TextField txtTypePKW;
    @FXML
    private ComboBox cbPropulsion;
    @FXML
    private ComboBox cbAvailable;
    @FXML
    private TextField txtDayPrice;
    @FXML
    private TextField txtPKWId;
    @FXML
    private Button btnChangeConfirmation;

    // Knopfen Befehlen
    @FXML
    private Button btnRead;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnExit;

    // Algemeine
    private int iOperationChosen;
    private int iIdPKW;

    // Anzeigen Gewaelte Operation
    @FXML
    private Label lblOperation;
    private Background bgColorInitialise;
    private Background bgColorDisplay;
    private Background bgColorUpdate;
    private Background bgColorAdd;
    private Background bgColorDelete;

    // Instandhaltung
    @FXML
    private Label lblSupOperation;
    @FXML
    private Label lblMaintenance;
    @FXML
    private ComboBox cbMaintenance;

    // Region 2. Konstruktor / Initialiser
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Algemeine Variablene initialisieren
        iOperationChosen = DEFAULT;
        iIdPKW = DEFAULT;

        //Initialisieren ComboBox
        initComboBox();

        //Initialisieren Hintergrundfarben
        initBackgroundColor();

        // Warnmeldungen initialisieren
        this.popUpWarnings = new PopUpWarnings();

        // Anfang Anwendung
        initialiseGUI();
    }
    //endregion

    // Region 3. Hauptmethoden
    /**
     * Initialisieren der Anwendung beim ersten Mal hochfahren und beenden des Programms
     */
    @FXML
    public void initialiseGUI() {
        // Ausgeben gewaelte Operation
        displayTypeOfOperation(OPERATION_INITIALISE, COMMAND_INITIALISE, bgColorInitialise);

        // Abschalten Eingabefelden
        toggleEntreeFields(NOT_EDIBLE, NOT_EDIBLE, NOT_VISIBLE);

        // Ausgabefeld entleeren
        lvDisplay.getItems().clear();

        //Knopfen befehlen sperren
        commandBlock(NOT_EDIBLE);

        //Sperren Block Instandhaltung
        maintenanceBlock(NOT_VISIBLE);

        //Entleeren und Entsperren Felden Anmelden
        logInBlock(EDIBLE);

        // Notiz Anmelden ausgeben
        lblLogInNote.setText(LOG_IN_NOTE);
        lblLogInNote_2.setText(LOG_IN_NOTE_EMPTY);
    }

    /**
     * Ueberpruefen Nutzername und Kennwort. Ueberpruefung wird von {@link PKWAssistance} <br>
     * uebernommen und einen ReturnCode wird zurueckgeschickt
     */
    @FXML
    public void checkUserAndPassword(){
        String strUser = txtUser.getText();
        String strPassword=pwPassword.getText();
        System.out.println(strUser + strPassword);

        // Uebergabe Ueberpruefung an PKWAssistance
        int iReturnCode = PKWAssistance.checkUserAndPassword(strUser, strPassword);

        // Auslesen ReturnCode von PKWAssistance
        switch (iReturnCode){
            case AppTexts.MATCH -> releaseApplication(strUser);
            case USER_NAME_EMPTY -> popUpWarnings.popUpMessage(MESSAGE_USER_NAME_EMPTY);
            case NO_MATCH_USER_NAME_PASSWORD -> popUpWarnings.popUpMessage(MESSAGE_NO_MATCH_USER_PASSWORD);
            default -> System.out.println(NO_SUCH_OPTION);
        }
    }

    /**
     * Wenn Zugangskontrolle erfolgreich war wird das Programm freigegeben
     * @param strUser : String : Nutzername wird ausgeben auf Willkommen Label
     */
    public void releaseApplication(String strUser){
        //Entleeren und sperren Felden Anmelden
        logInBlock(NOT_EDIBLE);

        // Notiz Wilkommen ausgeben
        lblLogInNote.setText(LOG_IN_WELCOME);
        lblLogInNote_2.setText(strUser);

        //Entsperren Knopfen Befehlen
        commandBlock(EDIBLE);

        // Falls Nutzer Supervisor Berechtigung hat, wird Block fuer Instandhaltung freigegeben
        if(PKWAssistance.strPrivilege.equals(SUPERVISOR)){
            //Ensperren Block Instandhaltung
            maintenanceBlock(VISIBLE);
        }
    }

    /**
     * Befehl zur Aufflisten der PKWs
     */
    @FXML
    public void startRead() {
        // Ausgeben gewaelte Operation
        displayTypeOfOperation(OPERATION_DISPLAY, COMMAND_DISPLAY, bgColorDisplay);

        //Sperren Eingabefelden
        toggleEntreeFields(NOT_EDIBLE,NOT_EDIBLE,NOT_VISIBLE);

        // Aufsetzen ListView
        initListView();
    }

    /**
     * Befehl zur Aktualisierung eines Datensatzes der DatenBank
     */
    @FXML
    public void startUpdate() {
        // Ausgeben gewaelte Operation
        displayTypeOfOperation(OPERATION_CHANGE, COMMAND_UPDATE, bgColorUpdate);

        // Nutzer ausrichten
        this.popUpWarnings.popUpMessage(USER_MSG_INPUT_FILL_IN_ID);

        // Eingabefelde Id verfuegbar stellen
        toggleEntreeFields(NOT_EDIBLE,EDIBLE,NOT_VISIBLE);
    }

    /**
     * Befehl zur Zufuegen eines PKWs
     */
    @FXML
    public void startAdd() {
        // Ausgeben gewaelte Operation
        displayTypeOfOperation(OPERATION_ADD, COMMAND_ADD, bgColorAdd);

        // Eingabefelden frei geben ohne Feld ID
        toggleEntreeFields(EDIBLE, NOT_EDIBLE,VISIBLE);

        // Werten in Eingabefelden entleeren
        txtTypePKW.clear();
        txtDayPrice.clear();
        txtPKWId.clear();

    }

    /**
     * Befehl zur Löeschen eines PKWs
     */
    @FXML
    public void startDelete() {
        // Ausgeben gewaelte Operation
        displayTypeOfOperation(OPERATION_DELETE, COMMAND_DELETE,bgColorDelete);

        // Eingabefelden sperren, Feld ID freigeben
        toggleEntreeFields(NOT_EDIBLE,EDIBLE,NOT_VISIBLE);

        // ID Feld entleeren
        txtPKWId.clear();

        // Fenster oeffnen um Nutzer zu berichten
        this.popUpWarnings.popUpMessage(USER_MSG_INPUT_FILL_IN_ID);
    }

    /**
     * Befehl zur Instandhaltung. Die eigentliche Anweisungen werden durch <br>
     * {@link PKWAssistance} uebernommen
     */
    @FXML
    public void startMaintenance(){
        int iOptionChosen = DEFAULT;

        // Auslesen gewaehlte Option
        String strMaintenanceOption = (String) cbMaintenance.getValue();

        if (strMaintenanceOption.equals(LOAD_SUPPLIER_A)){
            iOptionChosen = COMMAND_LOAD_SUPPLIER_A;     }
        if (strMaintenanceOption.equals(LOAD_SUPPLIER_B)){
            iOptionChosen = COMMAND_LOAD_SUPPLIER_B;     }
        if (strMaintenanceOption.equals(CREATE_BACK_UP)){
            iOptionChosen = COMMAND_CREATE_BACK_UP;     }
        if (strMaintenanceOption.equals(RESTORE_BACK_UP)){
            iOptionChosen = COMMAND_RESTORE_BACK_UP;     }

        // Gewaehlte Option zur Verarbeitung  nach PKWAssistance
        if(iOptionChosen!= DEFAULT){
            PKWAssistance.maintenanceOfApplication(iOptionChosen);
        } else {
            System.out.println(NO_SUCH_OPTION);
        }
    }

    /**
     * Von zwei Befehlen, Aenderung/Update und Loeschen/Delete soll die ID ueberprueft<br>
     * werden. Nach Freigabe werden fuer Option Aenderung/Update die Werten aus dem DB<br>
     * ausgelesen und nach den Eingabefelden geschickt(obwohl das vielleicht unnoetig<br>
     * ist mit ListView
     */
    @FXML
    public void idConfirmation() {
        // Auslesen ID
        String strCheckId = txtPKWId.getText();

        //Ueberpruefung ID
        if(checkId(strCheckId)){
            // Veroeffentlichen Knopf betaetigen
            btnChangeConfirmation.setVisible(VISIBLE);

            // Falls Operation Aenderen gewaelt wuerde
            if (iOperationChosen == AppCommands.COMMAND_UPDATE) {
                // Auslesen Werten aus DB
                PKW currentSelectedPKW = DbManager.getInstance().getRecordById(iIdPKW);

                // Einspeisen Werten in Eingabefelden
                txtTypePKW.setText(currentSelectedPKW.getType());
                cbPropulsion.setValue(currentSelectedPKW.getPropulsion());
                cbAvailable.setValue(Boolean.toString(currentSelectedPKW.isAvailable()));
                txtDayPrice.setText(Double.toString(currentSelectedPKW.getDayPrice()));

                // Eingabefelden freigeben, ID sperren
                toggleEntreeFields(EDIBLE,NOT_EDIBLE,VISIBLE);
            }
        } else {
        // ID Eingabefeld wieder sperren
        toggleEntreeFields(NOT_EDIBLE, EDIBLE,NOT_VISIBLE);
        }
    }

    /**
     * Bei Operationen Aendern, Zufuegen und Loeschen wird diese Bestaetzigung <br>
     * gefragt. Erst soll mal abfragen ob Loeschen die Bestaetigung hat eingegeben, <br>
     * wenn nicht, dann werden Aendern, Zufuegen betreut.
     */
    @FXML
    public void changeConfirmation() {

        // Operation PKW Loeschen
        if (iOperationChosen == COMMAND_DELETE) {
            DbManager.getInstance().deleteRecordInDbTblById(iIdPKW);
        } else {
            // Ueberpruefen der Eingabefelden
            if(inputFieldsOK()){

                // Operation Aenderungen auslesen und  speichern
                if (iOperationChosen == AppCommands.COMMAND_UPDATE) {
                    DbManager.getInstance().updateRecordInDbTbl(getGUIFields());}

                // Operation Zuefuegen/ Add Eingabe auslesen und zufuegen
                if (iOperationChosen == AppCommands.COMMAND_ADD) {
                    DbManager.getInstance().insertPKWIntoDbTbl(getGUIFields());}
            }
        }
        // Aufsetzen ListView
        initListView();

        //Zuruecksetzen Eingabefelden
        toggleEntreeFields(NOT_EDIBLE,NOT_EDIBLE,NOT_VISIBLE);
    }
    //endregion

    // Region 4. Hilfsmethoden

    /**
     * Felden fuer Instandhaltung werden ein- und ausgeschaltet
     * @param notVisible : boolean : (nicht)Sichtbar
     */
    private void maintenanceBlock(boolean notVisible) {
        lblSupOperation.setVisible(notVisible);
        lblMaintenance.setVisible(notVisible);
        cbMaintenance.setVisible(notVisible);
    }

    /**
     *  Zugangskontrolle wird gesperrt / freigegeben
     *
     * @param bEdible : boolean : sperren / freigeben
     */
    private void logInBlock(boolean bEdible) {
        txtUser.clear();
        pwPassword.clear();
        txtUser.setDisable(bEdible);
        pwPassword.setDisable(bEdible);
    }

    /**
     *  Ausgabe Type Operation
     *
     * @param strOperation : String : Text fuer jeweilige Operation
     * @param iOperationCommand : int : Nummer der Operation
     */
    private void displayTypeOfOperation(String strOperation, int iOperationCommand, Background bgColor ) {
        lblOperation.setText(strOperation);
        lblOperation.setBackground(bgColor);
        iOperationChosen = iOperationCommand;
    }

    /**
     *  Uebrpruefen eingegebe ID
     *
     * @param strCheckId : String : Eigegebene Text
     * @return bCheck : boolean : wenn Eingabe ID ok ist und besteht, -> true
     */
    private boolean checkId(String strCheckId) {
        boolean bCheck = true;

        // Leereingabe üeberpruefen
        if(notEmptyInput(strCheckId)){

            // Umsetzen von String nach Integer
            try {
                iIdPKW = Integer.parseInt(strCheckId);

                //Ueberpruefen ob die ID in DB besteht
                if(!PKWAssistance.idInTheList(iIdPKW, pkwList)){

                    // Falls ID nicht besteht, Meldung ausgeben und
                    this.popUpWarnings.popUpMessage(MESSAGE_WRONG_RECORD);

                    // ...Rüeckmeldung auf Falsch setzen
                    bCheck = false;
                }
            } catch (Exception e) {
                // Wenn Eingabeumwantlung fehlerhaft war, Meldung ausgeben
                this.popUpWarnings.popUpMessage(MESSAGE_WRONG_ENTRY);

                //..und Rüeckmeldung auf Falsch setzen
                bCheck = false;
            }
        } else {
            // Falls Eingabefeld leer war, Rüeckmeldung auf Falsch setzen
            bCheck = false;
        }
    return bCheck;
    }

    /**
     * Methode zur Ueberpruefung von Leereingabe
     * @param strCheckInput : String : Eingabetext zur Ueberpruefung
     * @return bNotEmpty : boolean : Wenn nicht leer ist -> true / ok
     */
    private boolean notEmptyInput(String strCheckInput) {
        boolean bNotEmpty = true;

        // Ueberpruefen ob leer ist
        if(strCheckInput.isEmpty() || strCheckInput.isBlank()){
            this.popUpWarnings.popUpMessage(MESSAGE_EMPTY_FIELD);
            bNotEmpty = false;
        }
        return bNotEmpty;
    }

    // Methoden zur Sperren und Freigeben felden
    private void commandBlock(boolean edible) {
        btnRead.setDisable(edible);
        btnUpdate.setDisable(edible);
        btnAdd.setDisable(edible);
        btnDelete.setDisable(edible);
        btnExit.setDisable(edible);
    }

    /**
     * Eingabefelden in einem Block zusammengepackt und mit Uebergabeparameter <br>
     * bedient entsprechend der Operation
     * @param bEntryFields : boolean : um Eingabefelden zu setzen
     * @param bIdField : boolean : um ID Feld zu Setzen
     * @param bConfirmation : boolean : um Bestaetigung zu sperren / freizugeben
     */
    private void toggleEntreeFields(boolean bEntryFields, boolean bIdField, boolean bConfirmation) {

        // Felden zur Aenderen / Zufuegen
        txtTypePKW.setDisable(bEntryFields);
        cbPropulsion.setDisable(bEntryFields);
        cbAvailable.setDisable(bEntryFields);
        txtDayPrice.setDisable(bEntryFields);

        // ID Feld
        txtPKWId.setDisable(bIdField);

        // Betaetigungsfeld
        btnChangeConfirmation.setVisible(bConfirmation);
    }

    /**
     * Fuer Operationen Aenderen und Zufuegen werden alle Angaben ueberprueft <br>
     * ob sie nicht leer sind und Umwntlung der Preise gut gegangen ist
     * @return bInputFieldsOK : boolean : true / false wird zurueckgeschickt
     */
    private boolean inputFieldsOK() {
        boolean bInputFieldsOK = false;

        // Alle Felden sollen nicht leer sein und die Preise soll richtig eingefuellt sein
        if(notEmptyInput(txtTypePKW.getText()) &&
            notEmptyInput(String.valueOf(cbPropulsion.getValue())) &&
            notEmptyInput(String.valueOf(cbAvailable.getValue())) &&
            notEmptyInput(txtDayPrice.getText()) &&
            priceConversionOK(txtDayPrice.getText())){

                //.. nur dann wird Genehmigung ausgegeben
                bInputFieldsOK = true;
        }
        return bInputFieldsOK;
    }

    /**
     * Umwandlung der Preise wird hier separat ausgefuehrt
     * @param strDayPrice : String : Eingabetext
     * @return bPriceConversion : boolean : Genehmigung falls Umwandlung geklappt hat
     */
    private boolean priceConversionOK(String strDayPrice) {
      boolean bPriceConversion = true;

      //Erste ueberpruefen ob Komma statt Punkt verwendet wurde
      strDayPrice = strDayPrice.replace(OLD_CHAR, NEW_CHAR);
        System.out.println(strDayPrice);

      try{
          Double.parseDouble(strDayPrice);

      } catch (Exception e){
          bPriceConversion = false;
          popUpWarnings.popUpMessage(MESSAGE_DOUBLE_CONVERSION_ERROR);
      }
        return bPriceConversion;
    }

    /**
     * Alle Eingabefelden werden in eine Methode<br>
     * ausgelesen und verpackt in PKW Instanz. Die richtige Eingabe sind zu bevor <br>
     * ueberprueft
     * @return : currentSelectedPKW : Instanz wird zurueckgegeben
     */
    private PKW getGUIFields() {
        PKW currentSelectedPKW = new PKW();

        currentSelectedPKW.setPKWId(iIdPKW);
        currentSelectedPKW.setType(txtTypePKW.getText());
        currentSelectedPKW.setPropulsion(String.valueOf(cbPropulsion.getValue()));
        currentSelectedPKW.setAvailable(Boolean.parseBoolean(String.valueOf(cbAvailable.getValue())));
        currentSelectedPKW.setDayPrice(PKWAssistance.doubleNicelyFormatted(txtDayPrice.getText()));

        return currentSelectedPKW;
    }

        //endregion

        // Region 5 Elementen JAVAFX werden initializiert
        private void initComboBox(){

        // Fuellen Combobox mit Antrieben
            ObservableList<String> options =
                    FXCollections.observableArrayList(PROPULSION_ELEC,
                            PROPULSION_DIESEL, PROPULSION_GAS, PROPULSION_HYBRIDE);

            cbPropulsion.getItems().addAll(options);

            // Fuellen Combobox Verfuegbarkeit
             options = FXCollections.observableArrayList(NOT_AVAILABLE,AVAILABLE);

            cbAvailable.getItems().addAll(options);

            // Fuellen Combobox Instandhaltung
            options = FXCollections.observableArrayList(LOAD_SUPPLIER_A, LOAD_SUPPLIER_B, CREATE_BACK_UP, RESTORE_BACK_UP);

            cbMaintenance.getItems().addAll(options);
        }

        private void initListView(){
            //Bestimmtdas vertikal gescrollt wird
            this.lvDisplay.setOrientation(Orientation.VERTICAL);

            //Generiert den CallBack welcher die einzelenen Items/Zellen generiert
            this.listViewCellFactory = new ListViewCellFactory();

            //Neue aktualsierte Liste setzen
            this.pkwList = DbManager.getInstance().getAllRecordsFromDb();

            // Liste in eine ObservableList verwandelen
            ObservableList observablePKW = FXCollections.observableList(pkwList);

            lvDisplay.getItems().clear();
            lvDisplay.setItems(observablePKW);

            /*
             * Die CellFactory bestimmt das ausseh der einzelen ListViewItems
             * aehnlich wie in Android der Adapter.
             */
            this.lvDisplay.setCellFactory(this.listViewCellFactory);

            //OnItemCellClick hinzufuegen
            this.lvDisplay.getSelectionModel().selectedItemProperty().addListener(this::onItemCellClick);

        }
    /**
     * In dem alle benoetigten Daten eines {@link PKW}s
     * von der Datenbank geleaden werden und in den Textfelder angezeigt werden
     *
     * @param observableList : {@link ObservableValue} - {@link PKW} : Tut Dinge
     * @param previousTrip   : {@link PKW} : Vorherig selektierter Eintrag
     * @param selectedTrip   : {@link PKW} : Eigentlicher selektierter Eintrag
     */
    private void onItemCellClick(ObservableValue<? extends PKW> observableList,
                                 PKW previousTrip, PKW selectedTrip) {

        if ((selectedTrip != null) && (!selectedTrip.equals(previousTrip))) {
            this.setGuiDataFromCurrentSelectedTrip(selectedTrip);
        }
    }

    private void setGuiDataFromCurrentSelectedTrip(PKW selectedTrip) {

        this.currentSelectedPKW = selectedTrip;

        this.txtPKWId.setText(Integer.toString(this.currentSelectedPKW.getPKWId()));
        this.txtTypePKW.setText(this.currentSelectedPKW.getType());
        this.cbPropulsion.setValue(this.currentSelectedPKW.getPropulsion());
        this.txtDayPrice.setText(Double.toString(this.currentSelectedPKW.getDayPrice()));
    }

    private void initBackgroundColor(){
        this.bgColorInitialise = new Background(new BackgroundFill(Paint.valueOf(BG_COLOR_INITIALISE), null, null));
        this.bgColorDisplay = new Background(new BackgroundFill(Paint.valueOf(BG_COLOR_DISPLAY), null, null));
        this.bgColorUpdate = new Background(new BackgroundFill(Paint.valueOf(BG_COLOR_UPDATE), null, null));
        this.bgColorAdd = new Background(new BackgroundFill(Paint.valueOf(BG_COLOR_ADD), null, null));
        this.bgColorDelete = new Background(new BackgroundFill(Paint.valueOf(BG_COLOR_DELETE), null, null));

    }

    //endregion
}



