package de.dlaan.dlmietwagenverwaltung.testdata;

import de.dlaan.dlmietwagenverwaltung.assistance.PKWAssistance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestStuff {
    // Region 0. Konstanten
    //endregion

    // Region 1. Variabelen und Attributen
    //endregion

    // Region 2. Konstruktor
    //endregion

    // Region 3. Methoden
    // main method
    public static void main(String[] args){

        double input = 3.14159265359;
        System.out.println("double : " + input);

        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        double newInput = bd.doubleValue();
        System.out.println("double : " + newInput);

 /*      System.out.println("double : " + newInput);
        String strTest = "123.456";
        System.out.println("Stuff: " + PKWAssistance.doubleNicelyFormatted(strTest));
        strTest = "123";
        System.out.println("Stuff: " + PKWAssistance.doubleNicelyFormatted(strTest));
        strTest = "100000,23";
        System.out.println("Stuff: " + PKWAssistance.doubleNicelyFormatted(strTest));
*/
        //     TestData testData = new TestData();
 //    List<PKW> listDummycars = testData.fillPKWListWithDummiwies();
        //        for (PKW currentPKW : listDummyVcars) {
        //             System.out.println(currentPKW.getFirstItems());
    //    }

 //       PKW testPKW = new PKW(1, "Mercedes E500", "Electric", true, 1000.00);
  //      String strCsvLine = "1; \"Mercedes E500\", \"Electric\", true, 1000.00"

 //          DbManager.getInstance().insertPKWsIntoDbTbl(listDummycars);
 /*       List<PKW> listDummycars = new ArrayList<>();

        listDummycars = DbManager.getInstance().getAllTripsFromDb();

        for (PKW currentPKW:listDummycars
             ) {
           System.out.println(currentPKW.toString());

        PKW testPKW = testData.fillPKWWithDummiwies();
        DbManager.getInstance().updatePKWInDbTbl(testPKW);
     DbManager.getInstance().deleteTripInDbTblById(4);

     testPKW = DbManager.getInstance().getAllPKWsFromDb();
        CsvFileHandler.getCsvFileHandlerInstance().saveCsvFileToDisc(testPKW);

        List<PKW> testPKW = new ArrayList<>();
        testPKW = CsvFileHandler.getCsvFileHandlerInstance().readCsvFileFromDisc();
        DbManager.getInstance().insertPKWsIntoDbTbl(testPKW);
 */
    }


    }

