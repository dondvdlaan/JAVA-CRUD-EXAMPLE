package de.dlaan.dlmietwagenverwaltung.logic.db;

import de.dlaan.dlmietwagenverwaltung.model.PKW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

/*
* Datenbank manager zur Kommunikation mit PKW Datenbank
 */
public class DbManager {
    // Region 0. Konstanten
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    private static final String DB_LOCAL_SERVER_IP_ADDRESS = "localhost";
    private static final String DB_LOCAL_NAME = "/mietwagenverwaltung";

    private static final String DB_LOCAL_CONNECTION_URL =
            "jdbc:mariadb://" + DB_LOCAL_SERVER_IP_ADDRESS + DB_LOCAL_NAME;

    private static final String DB_LOCAL_USER_NAME = "root";
    private static final String DB_LOCAL_USER_PW = "";

    //endregion

    // Region 1. Variabelen und Attributen
    private static DbManager instance;
    /**
     * Zugriff auf die Datenbanktablle tblUser
     */
    private DaoPKW daoPKW;
    //endregion

    // Region 2. Konstruktor
    /**
     * Standardkonstruktor
     */
    private DbManager() {
        this.daoPKW = new DaoPKW();
    }

    //endregion

    // Region 3. Getter und Setters
    /**
     * Gibt einzige Instanz zurück, Singleton
     *
     * @return instance : DbManager : Einzige Instanz
     */
    public static synchronized DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }

        return instance;
    }
    //endregion

    // Region 4. Verbindung zur DB

    /**
     * Gibt eine generiert Datenbankverbindung mit Lese(r) als auch Schreibrechten(w)
     * zurueckt oder null sollte etwas schiefgelaufen sein.
     *
     * @return rwDbConnection : {@link Connection} : Verbindung zur Datenbank mit rw - Rechten
     */
    private Connection getRwDbConnection() throws Exception {
        Connection rwDbConnection = null;

        try {
            //1: Registeren des JDBC driver
            Class.forName(JDBC_DRIVER);

            //2. Offenen einer Verbindung
            rwDbConnection = DriverManager.getConnection(DB_LOCAL_CONNECTION_URL, DB_LOCAL_USER_NAME, DB_LOCAL_USER_PW);

        } catch (SQLNonTransientConnectionException sqlNoConnectionEx) {
            throw new Exception("Keine Datenbankverbindung");
        } catch (ClassNotFoundException classNotFoundEx) {
            throw new Exception("JDBC Treiber konnte nicht geladen werden");
        }

        return rwDbConnection;
    }

    /**
     * Checkt ob die Datenbank online ist oder nicht
     *
     * @return isOnline : boolean : true : Dbist Online : false nicht
     */
    public boolean isDatabaseOnline() {
        boolean isOnline = true;
        try {
            this.getRwDbConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            isOnline = false;
        }
        return isOnline;
    }
    //endregion

    // Region 5 CRUD mit DB
    // INSERT
    /**
     * Einfuegen eines einzelen {@link PKW}s in die Datenbank
     *
     * @param pkwToInsert : {@link PKW} : Zum einfuegen in die Datenbank
     */
    public void insertPKWIntoDbTbl(PKW pkwToInsert) {

        try {
            if (this.isDatabaseOnline()) {
                //Neue Verbindung erstellen
                this.daoPKW.insertDataRecordIntoDbTbl(this.getRwDbConnection(), pkwToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Einfuegen meherer {@link PKW} in Datenbank
     *
     * @param pkwsToInsert : {@link PKW} - {@link PKW} : Reise zum einfuegen in die Datenbannk
     */
    public void insertPKWsIntoDbTbl(List<PKW> pkwsToInsert) {

        try {
            if (this.isDatabaseOnline()) {
                this.daoPKW.insertDataRecordsIntoDbTbl(this.getRwDbConnection(), pkwsToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    // READ
    /**
     * Liest eine bestimmtte PKW aus der Datenbank aus.
     *
     * @param iId : int : Id der PKW die auslgesen werden soll
     * @return specificPKWFromDbById : {@link PKW} : Ausgelsener PKW oder ein leeres Objekt
     * sollte das PKW nicht gefunden werden.
     */
    public PKW getRecordById(int iId) {
        PKW specificRecordFromDbById = new PKW();

        try {
            if (this.isDatabaseOnline()) {
                //Neue Verbindung erstellen
                specificRecordFromDbById =
                        this.daoPKW.getSpecificDataRecordFromDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return specificRecordFromDbById;
    }
    /**
     * Liest alle Daten aus der Tabelle aus
     *
     * @return allUsersFromDbTable : {@link List} - {@link PKW}: Alle Noizen aus Db-Tabelle
     */
    public List<PKW> getAllRecordsFromDb() {
        //Neue Verbindung erstellen
        List<PKW> allRecordsFromDb = new ArrayList<>();

        try {
            if (this.isDatabaseOnline()) {
                allRecordsFromDb = this.daoPKW.getAllDataRecordsFromDbTbl(this.getRwDbConnection());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return allRecordsFromDb;
    }

    // Update
    /**
     * Updated den mitgegeben PKW in der Datenbank.
     *
     * @param pkwToUpdate : {@link PKW} : PKW desseen Daten geaendert werden sollen.
     */
    public void updateRecordInDbTbl(PKW pkwToUpdate) {
        try {
            if (this.isDatabaseOnline()) {
                //Neue Verbindung erstellen
                this.daoPKW.updateDataRecordIntoDbTbl(this.getRwDbConnection(), pkwToUpdate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Delete
    /**
     * Loescht den {@link PKW} mit der uebergenen id aus
     * der Datenbanktabelle
     *
     * @param iId : int : Id der {@link PKW} die geloescht werden soll
     */
    public void deleteRecordInDbTblById(int iId) {
        //Neue Verbindung erstellen
        try {
            if (this.isDatabaseOnline()) {
                this.daoPKW.deleteDataRecordInDbTblById(this.getRwDbConnection(), iId);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    //endregion

}
