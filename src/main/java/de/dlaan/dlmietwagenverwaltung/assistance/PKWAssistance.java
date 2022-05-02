package de.dlaan.dlmietwagenverwaltung.assistance;

import de.dlaan.dlmietwagenverwaltung.logic.filehandler.CsvFileHandler;
import de.dlaan.dlmietwagenverwaltung.logic.db.DbManager;
import de.dlaan.dlmietwagenverwaltung.model.PKW;
import de.dlaan.dlmietwagenverwaltung.model.User;
import de.dlaan.dlmietwagenverwaltung.settings.AppCommands;
import de.dlaan.dlmietwagenverwaltung.settings.AppTexts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Diese Klasse stellt Methoden zur Verfuegung fuer GuiController
 */
public class PKWAssistance {
    // Region 0. Konstanten
    private static final String PATH_LOAD_SUPPLIER_A = "src/main/resources/de/dlaan/dlmietwagenverwaltung/zulieferanten/AVIS Mietwagen.csv";
    private static final String PATH_LOAD_SUPPLIER_B = "src/main/resources/de/dlaan/dlmietwagenverwaltung/zulieferanten/SIXTMietwagen.csv";
    private static final String PATH_CREATE_BACK_UP = "src/main/resources/de/dlaan/dlmietwagenverwaltung/back_up/DbBack_Up.txt";
    private static final String PATH_RESTORE_BACK_UP = "src/main/resources/de/dlaan/dlmietwagenverwaltung/back_up/DbBack_Up.txt";
    private static final String PATH_USERS_FILE = "C:\\java\\DlMietWagenVerwaltung\\src\\main\\resources\\de\\dlaan\\dlmietwagenverwaltung\\userdata\\Users.txt";

    private static final int DEFAULT = -1;
    public static final int DIGITS = 2;
    //endregion

    // Region 1. Variabelen und Attributen
    // Berechtigung Nutzer ist Anwender oder Administrador
    public static String strPrivilege;

    private static List<PKW> listPKWs;
    private static List<User> listUsers;

    //endregion

    // Region 2. Konstruktor

    /**
     * Privet Konstruktor um Objektinstanziierung zu vermeiden
     */
    private PKWAssistance() {
    }

    //endregion

    // Region 3. Methoden

    /**
     * Methode um die DB instandzuhalten. Diese PKWAssistance Klasse soll die <br>
     * GuiController ein bisschen entlasten.
     *
     * @param iOptionChosen : int : Befehl gemaess AppTexts wird ausgelesen und Datein verarbeitet
     */
    public static void maintenanceOfApplication(int iOptionChosen) {

        // Lesen gewaehlte Operation
        switch (iOptionChosen) {

            // Lesen Datei Zuliefant A und nach DB senden
            case AppCommands.COMMAND_LOAD_SUPPLIER_A -> {
                listPKWs = CsvFileHandler.getCsvFileHandlerInstance().readCsvFileFromDisc(PATH_LOAD_SUPPLIER_A);
                DbManager.getInstance().insertPKWsIntoDbTbl(listPKWs);
            }
            // Lesen Datei Zuliefant B und nach DB senden
            case AppCommands.COMMAND_LOAD_SUPPLIER_B -> {
                listPKWs = CsvFileHandler.getCsvFileHandlerInstance().readCsvFileFromDisc(PATH_LOAD_SUPPLIER_B);
                DbManager.getInstance().insertPKWsIntoDbTbl(listPKWs);
            }
            // Datei Sicherungskopie erzeugen und auf Festplatte speichern
            case AppCommands.COMMAND_CREATE_BACK_UP -> {
                listPKWs = DbManager.getInstance().getAllRecordsFromDb();
                CsvFileHandler.getCsvFileHandlerInstance().saveCsvFileToDisc(listPKWs, PATH_CREATE_BACK_UP);
            }
            // Datei Sicherungskopie laden und nach DB senden
            case AppCommands.COMMAND_RESTORE_BACK_UP -> {
                listPKWs = CsvFileHandler.getCsvFileHandlerInstance().readCsvFileFromDisc(PATH_RESTORE_BACK_UP);
                DbManager.getInstance().insertPKWsIntoDbTbl(listPKWs);
            }
            default -> System.out.println(AppTexts.NO_SUCH_OPTION);
        }
    }

    /**
     * Üeberpruefen Nutzername und Kennwort mit Datei gespeichert auf die Grstplatte
     * @param strUser : String : Uebergabe eingelsenen Nutzername aus GuiController
     * @param strPassword : String : Uebergabe eingelsenen Kennwort aus GuiController
     * @return iReturnCode : int : Zurueckgabe Erfolgs / Fehlercode iReturnCode an GuiController
     */
    public static int checkUserAndPassword(String strUser, String strPassword){
        int iReturnCode = DEFAULT;

        // Uebrepruefen Nutzername
        if(!strUser.isBlank() && !strUser.isBlank()){

            // Lesen Datei Nutzer
            listUsers = CsvFileHandler.getCsvFileHandlerInstance().readUserCsvFileFromDisc(PATH_USERS_FILE);

            // Ueberpruefen Zusammenhang Nutzer und Kennwort
            for (User currentUser:listUsers) {
                if(currentUser.getUserName().equals(strUser) && currentUser.getPassword().equals(strPassword)){

                    // Wenn Nutzername und Kennwort klappen, Nutzerberechtigung speichern
                    strPrivilege = currentUser.getPrivilege();

                    //..und Erfolgscode zurueckschicken
                    iReturnCode = AppTexts.MATCH;}
             }
             // Falls Nuthername und Kennwort nicht zusammenpassen, Fehlermeldung zurueckgeben
             if (iReturnCode != AppTexts.MATCH)
             { iReturnCode = AppTexts.NO_MATCH_USER_NAME_PASSWORD;}

          // Falls Nutzernameeingabe leer war, Fehlermeldung zurueckgeben
        } else { iReturnCode = AppTexts.USER_NAME_EMPTY;}
    return iReturnCode;
    }

    /**
     * Formatierung der strDayPrice in schöne Doublewert um gespeichert zu werden im DB
     * @param strDayPrice : String : Eingabe Parameter aus GUI
     * @return dblFormattedDouble : Double : Formatiertet Double zu Speicherung
     */
    public static double doubleNicelyFormatted(String strDayPrice){

        // Ueberpruefen ob Komma verwendet ist statt Punkt
        strDayPrice = strDayPrice.replace(',', '.');

        //Umwandeln nach einem Double
        double dblInput = Double.parseDouble(strDayPrice);
        System.out.println("double : " + dblInput);

        // Formatierung der Double
        BigDecimal bd = new BigDecimal(dblInput).setScale(2, RoundingMode.HALF_UP);
        double dblFormattedDouble = bd.doubleValue();
        System.out.println("double : " + dblFormattedDouble);

        return dblFormattedDouble;
    }

    /**
     * Ueberpruefen ob gewuenschte ID auch tatsaeglich im DB drin ist
     * @param iIdPKW : Mitgelieferte ID zur ueberpruefung
     * @return bIdInTheList : boolean : wenn ID gefunden, Methode sendet true zurueck
     */
    public static boolean idInTheList(int iIdPKW, List<PKW> pkwList) {
        boolean bIdInTheList = false;

        // Aus er Liste der PKWs die ID suchen
        for (PKW currentPKW:pkwList) {
            if(iIdPKW == currentPKW.getPKWId()){
                bIdInTheList = true;
            }
        } return bIdInTheList;
    }
    //endregion
}