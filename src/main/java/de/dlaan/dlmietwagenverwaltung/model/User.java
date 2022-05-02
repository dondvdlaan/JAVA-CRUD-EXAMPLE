package de.dlaan.dlmietwagenverwaltung.model;

/**
 * User Klasse um die Nutzer und Supervisors zu registrieren. Angabe der Nutzer<br>
 * werden mit einer CsvDatei gespeichert
 */
public class User {
    // Region 0. Konstanten
    private static final String DEFAULT_VALUE = "NothingToSeeHere";
    private static final int DEFAULT_INT = -1;

    private static final String SEPARATOR = ",";

    private static final int SEPARATOR_POS_FIRST_ATTRIBUTE = 0;
    private static final int SEPARATOR_POS_SECOND_ATTRIBUTE = 1;
    private static final int SEPARATOR_POS_THIRD_ATTRIBUTE = 2;

    //endregion

    // Region 1. Variabelen und Attributen
    private String strUserName;
    private String strPassword;
    private String strPrivilege;

    //endregion

    // Region 2. Konstruktor

    /**
     * Einfache Konstruktor ohne Parameters
     */
    public User() {
        this.strUserName = DEFAULT_VALUE;
        this.strPassword = DEFAULT_VALUE;
        this.strPrivilege = DEFAULT_VALUE;
    }
    /**
     * Ueberladene Konstruktor mit alle Attributen
     * @param strUserName : String : Nutzername
     * @param strPassword : String : Kennwort
     * @param iPrivilege : String : Berechtigung
     */
    public User(String strUserName, String strPassword, int iPrivilege) {
        this.strUserName = getUserName();
        this.strPassword = getPassword();
        this.strPrivilege = getPrivilege();
    }

    /**
     * Ueberladene Konstruktor leestZeile aus CsvDatei aus
     * @param strLineCsvFile : String : Satz aus CsvDatei
     */
    public User(String strLineCsvFile) {

        //Standardkonstruktor wird aufgerufen
        this();

        //Methode wird aufegerufen um Attributen aus Csv Datei zu trennen
        this.setAttributesFromCsvFile(strLineCsvFile);
    }
//endregion

    // Region 3. Getter und Setters
    public void setAttributesFromCsvFile(String strLineCsvFile){
        String[] currentUser = strLineCsvFile.split(SEPARATOR);

        //EXPLICIT CAST
        this.strUserName = currentUser[SEPARATOR_POS_FIRST_ATTRIBUTE];
        this.strPassword = currentUser[SEPARATOR_POS_SECOND_ATTRIBUTE];
        this.strPrivilege = currentUser[SEPARATOR_POS_THIRD_ATTRIBUTE];
    }

    public String getUserName() {
        return strUserName;
    }

    public void setUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getPassword() {
        return strPassword;
    }

    public void setPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getPrivilege() {
        return strPrivilege;
    }

    public void setPrivilege(String strPrivilege) {
        this.strPrivilege = strPrivilege;
    }

    //endregion

    // Region 4. toString

    @Override
    public String toString() {
        return "User{" +
                "strUserName='" + strUserName + '\'' +
                ", strPassword='" + strPassword + '\'' +
                ", iPrivilege=" + strPrivilege +
                '}';
    }

    //endregion
}
