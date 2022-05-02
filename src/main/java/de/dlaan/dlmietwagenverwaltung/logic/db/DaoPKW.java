package de.dlaan.dlmietwagenverwaltung.logic.db;

import de.dlaan.dlmietwagenverwaltung.logic.db.ASqlKeyWords;
import de.dlaan.dlmietwagenverwaltung.model.PKW;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

    /**
     * Diese Klasse ist eine DAO-Klasse.
     * DAO steht fuer Data Access Object.
     * Sie fuehrt die CRUD  Operationen aus auf auf der DbTabelle {@link PKW}
     */
    public class DaoPKW extends ASqlKeyWords {

        //region 0. Konstanten
        /**
         * Primaerschluessel aller Tabellen dieses Projekts
         * ansonsten koennnte man auch hier PRIMARY KEY als Namen
         * verwenden.
         */
        protected static final String COL_NAME_ID = "_id";
        protected static final String COL_NAME_ID_INC_COL_BACK_TICKS =
                CHAR_COL_BACK_TICK + COL_NAME_ID + CHAR_COL_BACK_TICK;

        protected static final String COL_NAME_TYPE = "type";
        protected static final String COL_NAME_TYPE_INC_COL_BACK_TICKS =
                CHAR_COL_BACK_TICK + COL_NAME_TYPE + CHAR_COL_BACK_TICK;

        protected static final String COL_NAME_PROPULSION = "propulsion";
        protected static final String COL_NAME_PROPULSION_INC_COL_BACK_TICKS =
                CHAR_COL_BACK_TICK + COL_NAME_PROPULSION + CHAR_COL_BACK_TICK;

        protected static final String COL_NAME_IS_AVAILABLE = "is_available";
        protected static final String COL_NAME_IS_AVAILABLE_INC_COL_BACK_TICKS =
                CHAR_COL_BACK_TICK + COL_NAME_IS_AVAILABLE + CHAR_COL_BACK_TICK;

        protected static final String COL_NAME_DAY_PRICE = "day_price";
        protected static final String COL_NAME_DAY_PRICE_INC_COL_BACK_TICKS =
                CHAR_COL_BACK_TICK + COL_NAME_DAY_PRICE + CHAR_COL_BACK_TICK;

        private static final String TABLE_NAME = "PKW";
        //endregion


        //region Insert

        /**
         * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
         *
         * @param dbRwConnection          : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @param tripToInsertIntoDbTable : {@link PKW : Model welches eingefuegt werden soll
         */
        public void insertDataRecordIntoDbTbl(Connection dbRwConnection, PKW tripToInsertIntoDbTable) {

            //Decl and Init
            Statement dbStatementToExecute = null;

            try {
                //1. Db Connection ist bereits von DbManger generiert

                //2. Statementobjekt zum tatsaechlichen ausfuehren des unten als String generierten SQL-Statements
                dbStatementToExecute = dbRwConnection.createStatement();

                //3. SQL-String angepasst auf die Tabelle generieren

                String strSqlStmtInsert = getSqlStmtInsert(tripToInsertIntoDbTable);

                //DEBUG
                System.out.println(">>>>>>>> " + strSqlStmtInsert);

                //4. SQL - String an Satement objekt zum ausfuerhren geben
                dbStatementToExecute.execute(strSqlStmtInsert);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }


        }


        /**
         * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
         *
         * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @param pkwsToInsertIntoDbTable : {@link PKW} : Models welches eingefuegt werden soll
         */
       public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<PKW> pkwsToInsertIntoDbTable) {
            //Decl and Init
            Statement dbStatementToExecute = null;

            try {
                //1. Db Connection ist bereits von DbManger generiert

                //2. Statementobjekt zum tatsaechlichen ausfuehren des unten als String generierten SQL-Statements
                dbStatementToExecute = dbRwConnection.createStatement();

                //Alle Datensaetze durchlaufen
                for (PKW pkwToInsertIntoDbTable : pkwsToInsertIntoDbTable) {

                    String strSqlStmtInsert = getSqlStmtInsert(pkwToInsertIntoDbTable);

                    //DEBUG
                    System.out.println(">>>>>>>> " + strSqlStmtInsert);

                    //4. SQL - String an Satement objekt zum ausfuerhren geben
                    dbStatementToExecute.execute(strSqlStmtInsert);

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }
        //endregion

        //region Update

        /**
         * Aendert einen einzelen Datensatz in der Datebanktabelle
         *
         * @param dbRwConnection        : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @param pkwToUpdateInDbTable : {@link PKW} : Model welches geaendert werden soll
         */
      public void updateDataRecordIntoDbTbl(Connection dbRwConnection, PKW pkwToUpdateInDbTable) {

            Statement dbStatementToExecute = null;
            try {
                //1. Db Connection ist bereits vom DbManager geoeffnet wordem

                //2. Statementobjek generieren lassen
                dbStatementToExecute = dbRwConnection.createStatement();

                /*
                 * UPDATE `PKW`
                 * SET
                 * `type`= 'type der Instanz'
                 * `propulsion`= 'propulsion der Instanz'
                 * `is_available`= 'is_available der Instanz'
                 * `day_price`= 'day_price der Instanz'
                 * WHERE _id = 5
                 */
             String strSqlStmtUpdate = UPDATE_TBL + TABLE_NAME
                     + SET_OPERATOR
                     + COL_NAME_TYPE_INC_COL_BACK_TICKS
                     + EQUALS_OPERATOR
                     + CHAR_VALUE_BACK_TICK + pkwToUpdateInDbTable.getType() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                     + COL_NAME_PROPULSION_INC_COL_BACK_TICKS
                     + EQUALS_OPERATOR
                     + CHAR_VALUE_BACK_TICK + pkwToUpdateInDbTable.getPropulsion() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                     + COL_NAME_IS_AVAILABLE_INC_COL_BACK_TICKS
                     + EQUALS_OPERATOR;
                     if (pkwToUpdateInDbTable.isAvailable()) {
                        strSqlStmtUpdate += DATA_TYPE_BOOLEAN_TRUE + CHAR_COMMA;
                     } else {
                        strSqlStmtUpdate += DATA_TYPE_BOOLEAN_FALSE + CHAR_COMMA;
                     }
                     strSqlStmtUpdate +=
                     COL_NAME_DAY_PRICE_INC_COL_BACK_TICKS
                     + EQUALS_OPERATOR
                     + CHAR_VALUE_BACK_TICK + pkwToUpdateInDbTable.getDayPrice() + CHAR_VALUE_BACK_TICK
                     + WHERE_CONDITION
                     + COL_NAME_ID_INC_COL_BACK_TICKS
                     + EQUALS_OPERATOR + pkwToUpdateInDbTable.getPKWId();

                //DEBUG
                System.out.println(">>>>>>>> " + strSqlStmtUpdate);

                //4. SQL - String an Satement objekt zum ausfuerhren geben
                dbStatementToExecute.executeUpdate(strSqlStmtUpdate);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }

        /**
         * Aendert eine Liste von Datensaetzen in der Datebanktabelle
         *
         * @param dbRwConnection         : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @param tripsToUpdateInDbTable : {@link Trip} : Models welches geaendert werden soll
         */

        // Nichte verwendet fuer diese Anwendung
/*        public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<Trip> tripsToUpdateInDbTable) {
            Statement dbStatementToExecute = null;
            try {
                //1. Db Connection ist bereits vom DbManager geoeffnet wordem

                //2. Statementobjek generieren lassen
                dbStatementToExecute = dbRwConnection.createStatement();

                for (Trip tripToUpdateInDbTable : tripsToUpdateInDbTable) {
                    //2. Statementobjek generieren lassen
                    dbStatementToExecute = dbRwConnection.createStatement();

                    /*
                     * UPDATE `trips`
                     * SET
                     * `is_uploaded`= '1'
                     * WHERE _id = 5
                     */
 /*                   String strSqlStmtUpdate = UPDATE_TBL + TABLE_NAME
                            + SET_OPERATOR
                            + COL_NAME_IS_UPLOADED_INC_COL_BACK_TICKS
                            + EQUALS_OPERATOR;


                    strSqlStmtUpdate += CHAR_VALUE_BACK_TICK;

                    if (tripToUpdateInDbTable.isUploaded()) {
                        strSqlStmtUpdate += DATA_TYPE_BOOLEAN_TRUE;
                    } else {
                        strSqlStmtUpdate += DATA_TYPE_BOOLEAN_FALSE;
                    }
                    strSqlStmtUpdate += CHAR_VALUE_BACK_TICK
                            + WHERE_CONDITION + COL_NAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR + tripToUpdateInDbTable
                            .getId();


                    //DEBUG
//                System.out.println(">>>>>>>> " + strSqlStmtUpdate);

                    //4. SQL - String an Satement objekt zum ausfuerhren geben
                    dbStatementToExecute.executeUpdate(strSqlStmtUpdate);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }
        //endregion


        //region Read
*/
        /**
         * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
         *
         * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link PKW} : Liste aller Datensaetze
         */
        public List<PKW> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {

            //Decl. and Init
            List<PKW> allRecordsFromDbTable = new ArrayList<>();

            Statement dbStatementToExecute = null;

            try {
                //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

                //2. Geneieren des Statenements
                dbStatementToExecute = dbRwConnection.createStatement();

                //3. Query generieren und absetzen und Ergebnismenge merken
                String strSqlStmtGetAll = SELECT_ALL_DATA_FROM + TABLE_NAME;

                ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetAll);

                //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
                while (resultSetFromExecutedQuery.next()) {

                    //5. Aus der Ergebenismenge einen User beschafften
                    PKW tripFromDbTable = this.getModelFromResultSet(resultSetFromExecutedQuery);

                    //6. Modelobjekt zur passenden Liste adden
                    allRecordsFromDbTable.add(tripFromDbTable);

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }

            return allRecordsFromDbTable;
        }

        /**
         * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
         *
         * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @param iId
         * @return specificDataRecordFoundById : {@link PKW}  oder abgeleitet davon: Gesuchtes Objekt oder null,
         * sollte es dieses nicht geben
         */
         public PKW getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
            PKW specificTrip = null;

            //Decl. and Init

            Statement dbStatementToExecute = null;

            try {
                //1. Rw Db Connection ist bereits vom DbManger geoeffenent und Integriert

                //2. Geneieren des Statenements
                dbStatementToExecute = dbRwConnection.createStatement();

                /*
                 * 3. Query generieren und absetzen und Ergebnismenge merken
                 * SELECT * FROM PKW WHERE _id = 1
                 *
                 */
                String strSqlStmtGetById =
                        SELECT_ALL_DATA_FROM + TABLE_NAME + WHERE_CONDITION + COL_NAME_ID + EQUALS_OPERATOR + iId;

                ResultSet resultSetFromExecutedQuery = dbStatementToExecute.executeQuery(strSqlStmtGetById);

                //4. ResultSet == Ergebnismenge durchlaufen bis kein Datensaezte mehr da sind
                if (resultSetFromExecutedQuery.first()) {

                    //5. Aus der Ergebenismenge einen User beschafften
                    specificTrip = this.getModelFromResultSet(resultSetFromExecutedQuery);

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }

            return specificTrip;
        }
        //endregion

        //region Delete

        /**
         * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
         *
         * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
         * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
         */
       public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
            Statement dbStatementToExecute = null;

            try {
                //1 Db Connection bereits vom DbManager geoeffent

                //2. Geneieren des Statenements
                dbStatementToExecute = dbRwConnection.createStatement();

                /*
                 * 3. Statement geneireren
                 * String strSqlDeleteUserById = "DELETE FROM `trips` WHERE `_id` = " + iId;
                 */

             String strSqlDeleteUserById = ASqlKeyWords.DELETE_FROM_TBL + TABLE_NAME + WHERE_CONDITION
                        + COL_NAME_ID_INC_COL_BACK_TICKS
                        + ASqlKeyWords.EQUALS_OPERATOR + iId;

                dbStatementToExecute.executeUpdate(strSqlDeleteUserById);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (dbStatementToExecute != null) {
                    //5. Schliessen der des Statements
                    try {
                        dbStatementToExecute.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }

                if (dbRwConnection != null) {
                    //6. Schliessen der Verbindung
                    try {
                        dbRwConnection.close();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                }
            }
        }
        //endregion

        //region Model aus ResultSet Formen

        /**
         * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
         *
         * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
         * @return trip : {@link PKW} : Model abgeleitet von der Basisklasse
         * @throws Exception
         */
        protected PKW getModelFromResultSet(ResultSet currentResultSet) throws Exception {
            //Index auslesen
//        final int iColumnIndexId          = currentResultSet.findColumn(COL_NAME_ID);
//        final int iColumnIndexName        = currentResultSet.findColumn(COL_NAME_NAME);
//        final int iColumnIndexDescription = currentResultSet.findColumn(COL_NAME_DESCRIPTION);
//        final int iColumnIndexTaxPrice    = currentResultSet.findColumn(COL_NAME_TAX_PRICE);
//        final int iColumnIndexIsUploaded  = currentResultSet.findColumn(COL_NAME_IS_UPLOADED);

            //6. Durch Auswahl des Datentyps und angabe des Spaltenindizes auselsen der Daten
            int iId = currentResultSet.getInt(COL_NAME_ID);

            String strType = currentResultSet.getString(COL_NAME_TYPE);
            String strPropulsion = currentResultSet.getString(COL_NAME_PROPULSION);
            boolean isAvailable = currentResultSet.getBoolean(COL_NAME_IS_AVAILABLE);
            double dblDayPrice = currentResultSet.getDouble(COL_NAME_DAY_PRICE);

            //7. Neues Modelobjekt generieren
            PKW pkwFromDb = new PKW();

            pkwFromDb.setPKWId(iId);

            pkwFromDb.setType(strType);
            pkwFromDb.setPropulsion(strPropulsion);
            pkwFromDb.setAvailable(isAvailable);
            pkwFromDb.setDayPrice(dblDayPrice);

            return pkwFromDb;
        }
        //endregion

    // Region xx Hilfsmethoden

        /**
         * Methode um SQL Stament fuer 1 oder mehrere PKW nach dem DB zu schicken
          * @param pkwToInsertIntoDbTable : einzele PKW Instanz
         * @return                          : String SQL Statement fuer DB
         */
        private String getSqlStmtInsert(PKW pkwToInsertIntoDbTable) {
    /*
     * INSERT INTO PKW(
     *      `type`,
     *      `propulsion`,
     *      `available`,
     *      `dayprice`
     * )
     *  VALUES (
     *      'Mercedes E500',
     *      'Verbrenner',
            true,
     *      600.00
     * );
     *
     */
            String strSqlStmtInsert = INSERT_TBL + TABLE_NAME +
                    CHAR_OPEN_PARENTHESIS
                    + COL_NAME_TYPE_INC_COL_BACK_TICKS + CHAR_COMMA
                    + COL_NAME_PROPULSION_INC_COL_BACK_TICKS + CHAR_COMMA
                    + COL_NAME_IS_AVAILABLE_INC_COL_BACK_TICKS + CHAR_COMMA
                    + COL_NAME_DAY_PRICE_INC_COL_BACK_TICKS
                    + CHAR_CLOSE_PARENTHESIS
                    + VALUES_OPERATOR +
                    CHAR_OPEN_PARENTHESIS
                    + CHAR_VALUE_BACK_TICK + pkwToInsertIntoDbTable.getType() + CHAR_VALUE_BACK_TICK + CHAR_COMMA
                    + CHAR_VALUE_BACK_TICK + pkwToInsertIntoDbTable.getPropulsion() + CHAR_VALUE_BACK_TICK + CHAR_COMMA;

            if (pkwToInsertIntoDbTable.isAvailable()) {
                strSqlStmtInsert += DATA_TYPE_BOOLEAN_TRUE;
            } else {
                strSqlStmtInsert += DATA_TYPE_BOOLEAN_FALSE;
            }

            strSqlStmtInsert += CHAR_COMMA + pkwToInsertIntoDbTable.getDayPrice() + CHAR_CLOSE_PARENTHESIS_SEMICOLON;

            return strSqlStmtInsert;
        }
    //endregion
}
