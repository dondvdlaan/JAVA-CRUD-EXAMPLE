package de.dlaan.dlmietwagenverwaltung.logic.filehandler;

import de.dlaan.dlmietwagenverwaltung.model.PKW;
import de.dlaan.dlmietwagenverwaltung.model.User;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Dieser Controller ist für Ein- und Auslesen der Dateien <br>
 * der Anwendung MietWagenVerwaltung. Der CsvFileHandler <br>
 * ist ein Singleton und kann nur einmal auf sichselbst Instanziert werden
 */
public class CsvFileHandler {
    // Region 0. Konstanten

    //endregion

    /**
     * Einzige Instanz die jemals existierten wird.
     */
    // Region 1. Variabelen und Attributen
    private static CsvFileHandler onlyInstanceOfCsvFileHandler;
    //endregion

    /**
     * Privat Konstruktor
     */
    // Region 2. Konstruktor
    private CsvFileHandler() {
    }

    //endregion

    // Region 3. Getter und Setters
    public static synchronized CsvFileHandler getCsvFileHandlerInstance() {
        /*
         * Hier wird nur ein einzige mal ein Instanz generiert von sichselbst <br>
         * generiert, sogennante Singleton
         */
        onlyInstanceOfCsvFileHandler = new CsvFileHandler();

        return onlyInstanceOfCsvFileHandler;
    }

    //endregion

    // Region 4. Speichern Datei

    /**
     * Speichern der mitgelieferde Datei. Zudem soll eingegeben werden wo sie <br>
     * gespeichert werden soll
     * @param listToSave : List<PKW> : Datei zu speichern
     * @param strPathToCsvFile : String : Ort wo Datei gespeichern werden soll
     */
    public void saveCsvFileToDisc(List<PKW> listToSave, String strPathToCsvFile ) {
        // 0. Instanzieren CSV Datei mit Pfad nach Datei auf der Festplatte
        File csvFile = new File(strPathToCsvFile);

        //1. Informationen :/ und Weg zur Datei kann nur in Bytes schreiben
        FileOutputStream fos = null;

        //2. Schreibt in Byte :/ und setzt den Zeichensatz
        OutputStreamWriter osw = null;

        //3. Schreibt Strings :)
        BufferedWriter out = null;

        try {
            //Checken ob es Diese datei bereits gibt
            if (!csvFile.exists()) {

                //Anlegen sollte keine Datei existieren
                csvFile.createNewFile();
            }

            //1. Fos generieren mit Dateiobjekt generieren
            fos = new FileOutputStream(csvFile, false);

            //2. osw Zeichensatz setz mit dem fos
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            //3. out mit osw generieren
            out = new BufferedWriter(osw);

            //4. Gesamte Liste durchwandern fue Objekt
            for (PKW s : listToSave) {
                // Separator wird zugefuegt
                out.write(s.getAttributesWithSeparatorForCsvFile());
            }

        } catch (Exception e) {
            System.err.println(strPathToCsvFile);
            e.printStackTrace();
        } finally {

            if (out != null) {
                try {
                    //5. Immer Inhalt in die Datei schreiben egal ob eine Exception auftritt oder nich
                    out.flush();

                    //6. Verbindung zur Datei schließen
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //endregion

    // Region 5. Lesen Datei

    /**
     *  Datei wird von Festplatte gelesen Zeile fuer Zeile
     *
     * @param strPathToCsvFile : Ort wo Datei liegt wird mitgeteilt
     * @return : listFromDisc Typ List<PKW> bekommt man zurueck
     */
    public List<PKW> readCsvFileFromDisc(String strPathToCsvFile) {
        // Deklarieren Datei / Objektarray für zurueckgabe
        List<PKW> listFromDisv = new ArrayList<>();
        // Anlegen csv Datei
        File csvFile = new File(strPathToCsvFile);

        //1. Informationen :/ und Weg zur Datei kann nur in Bytes lesen
        FileInputStream fis = null;

        //2. Liest in Byte :/ und setzt den Zeichensatz
        InputStreamReader isr = null;

        //3. Liest Strings :)
        BufferedReader in = null;

        try {
            //Checken ob es Diese datei bereits gibt
            if (csvFile.exists()) {

                //1. Fis generieren mit Dateiobjekt generieren
                fis = new FileInputStream(csvFile);

                //2. isr Zeichensatz setzen mit dem fis
                isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

                //3. in mit isr generieren
                in = new BufferedReader(isr);

                //End Of File
                boolean eof = false;

                //4. Sonalge das Ender der Datei nicht erreicht ist
                while (!eof) {

                    //5. Aktuelle Zeile der Datei auslesen
                    String strReadCsvLine = in.readLine();

                    //6. Checken ob das Ende der Datei erreicht ist
                    if (strReadCsvLine == null) {
                        eof = true;
                    } else {
                        //Ende der Datei noch nicht erreicht

                        //Neues Objekt anlegen
                        PKW s = new PKW();

                        //Mit Daten befuellen, Separator wird rausgefiltert
                        s.setAttributesFromCsvFile(strReadCsvLine);

                        //Objekt in die Liste machen / addieren
                        listFromDisv.add(s);
                    }

                }
            }
        } catch (Exception e) {
            System.err.println(strPathToCsvFile);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    //5. Immer Inhalt in die Datei schreiben egal ob eine Exception auftritt oder nich
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return listFromDisv;
    }

    /**
     * Datei der Nutzer wird von Festplatte gelesen Zeile fuer Zeile
     *
     * @return : listFromDisv Typ List<User> bekommt man zurueck
     */
    public List<User> readUserCsvFileFromDisc(String strPathToCsvFile) {
        // Deklarieren Datei / Objektarray für zurueckgabe
        List<User> listFromDisc = new ArrayList<>();
        // Anlegen csv Datei
        File csvFile = new File(strPathToCsvFile);

        //1. Informationen :/ und Weg zur Datei kann nur in Bytes lesen
        FileInputStream fis = null;

        //2. Liest in Byte :/ und setzt den Zeichensatz
        InputStreamReader isr = null;

        //3. Liest Strings :)
        BufferedReader in = null;

        try {
            //Checken ob es Diese datei bereits gibt
            if (csvFile.exists()) {

                //1. Fis generieren mit Dateiobjekt generieren
                fis = new FileInputStream(csvFile);

                //2. isr Zeichensatz setzen mit dem fis
                isr = new InputStreamReader(fis, StandardCharsets.UTF_8);

                //3. in mit isr generieren
                in = new BufferedReader(isr);

                //End Of File
                boolean eof = false;

                //4. Sonalge das Ender der Datei nicht erreicht ist
                while (!eof) {

                    //5. Aktuelle Zeile der Datei auslesen
                    String strReadCsvLine = in.readLine();

                    //6. Checken ob das Ende der Datei erreicht ist
                    if (strReadCsvLine == null) {
                        eof = true;
                    } else {
                        //Ende der Datei noch nicht erreicht

                        //Neues Objekt anlegen
                        //Mit Daten befuellen, Separator wird rausgefiltert
                        User s = new User(strReadCsvLine);

                        //Objekt in die Liste machen / addieren
                        listFromDisc.add(s);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(strPathToCsvFile);
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    //5. Immer Inhalt in die Datei schreiben egal ob eine Exception auftritt oder nich
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return listFromDisc;
    }
}
