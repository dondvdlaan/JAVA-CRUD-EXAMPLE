package de.dlaan.dlmietwagenverwaltung.settings;

/**
 * Texten fuer die Verwendungen werden hier abgelegt
 */
public class AppTexts {
    // Region 0. Konstanten
    // Aus Main
    public static final String APPLICATION_TEXT = "*** Basic Car Reservation System ***" ;

    // Nutzerkontrolle
    public static final String LOG_IN_NOTE = "Bitte anmelden";
    public static final String LOG_IN_NOTE_EMPTY = "";
    public static final String LOG_IN_WELCOME = "Willkommen Nutzer:";

    public static final String OPERATOR = "OPER";
    public static final String SUPERVISOR = "SUP";
    public static final int MATCH = 0;
    public static final int USER_NAME_EMPTY = 1;
    public static final int NO_MATCH_USER_NAME_PASSWORD = 2;

    // Gewaelte Operation
    public static final String OPERATION_INITIALISE = "=== Programm initialisiert ===";
    public static final String OPERATION_DISPLAY = "=== Aufzeigen Fahrzeugen ===";
    public static final String OPERATION_CHANGE = "=== Ändern Fahrzeugen ===";
    public static final String OPERATION_ADD = "=== Zufügen Fahrzeugen ===";
    public static final String OPERATION_DELETE = "=== Löschen Fahrzeugen ===";

    public static final String BG_COLOR_INITIALISE = "AQUAMARINE";
    public static final String BG_COLOR_DISPLAY = "BISQUE";
    public static final String BG_COLOR_UPDATE = "CORNSILK";
    public static final String BG_COLOR_ADD = "GAINSBORO";
    public static final String BG_COLOR_DELETE = "LAVENDER";

    // Aendern, Zufuegen
    public static final String USER_MSG_INPUT_FILL_IN_ID = "PKW Id eintragen bitte + Eingabe";
    public static final String USER_MSG_INPUT_DELETE = "Sind Sie sicher?";

    public static final String PROPULSION_ELEC = "Stromer";
    public static final String PROPULSION_GAS = "Brenner";
    public static final String PROPULSION_HYBRIDE= "Hybride";
    public static final String PROPULSION_DIESEL = "Diesel";

    // Instandhaltung
    public static final String RESTORE_BACK_UP = "Sicherungskopie laden";
    public static final String CREATE_BACK_UP = "Sicherungskopie erzeugen";
    public static final String LOAD_SUPPLIER_A = "AVIS PKWs laden";
    public static final String LOAD_SUPPLIER_B = "SIXT PKWs laden";

    // Warnungberichten
    public static final String MESSAGE_PAY_ATTENTION = "#### Hinweis beachten ###";
    public static final String MESSAGE_EMPTY_FIELD = "Eingabe war leer";
    public static final String MESSAGE_WRONG_ENTRY = "Das war ein Fehlangabe, versuchen es nochmal.";
    public static final String MESSAGE_WRONG_RECORD = "Diese Zeile gibt es nicht";
    public static final String MESSAGE_DOUBLE_CONVERSION_ERROR = "Eingabe für die Preise ist falsch";


    public static final String MESSAGE_USER_NAME_EMPTY = "Nutzername ist leer";
    public static final String MESSAGE_NO_MATCH_USER_PASSWORD = "Kein Zusammenhang Nutzername und Kennwort";

    public static final String NO_SUCH_OPTION = "DEBUG -> No such option";

    // Algemein
    public static final String CURRENCY = "€ / Tag";

    //endregion

    // Region 1. Variabelen und Attributen
    //endregion

    // Region 2. Konstruktor

    /**
     * Nur private Konstruktor
     */
    private AppTexts() {
    }

    //endregion

    // Region 3. Getter und Setters
    //endregion

    // Region 4. toString
    //endregion
}
