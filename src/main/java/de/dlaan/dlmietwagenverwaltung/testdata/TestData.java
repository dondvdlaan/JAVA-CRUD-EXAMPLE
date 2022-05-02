package de.dlaan.dlmietwagenverwaltung.testdata;

import de.dlaan.dlmietwagenverwaltung.model.PKW;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    // Region 0. Konstanten
    private static final int AMOUNT_OF_DUMMIES = 10;
    //endregion

    // Region 1. Variabelen und Attributen
    List<PKW> testListPKW;
    PKW currentPKW;

    //endregion

    // Region 2. Konstruktor
    //endregion

    // Region 3. Methoden
    public  PKW fillPKWWithDummiwies(){

            currentPKW = new PKW();
            currentPKW.setPKWId(3);
            currentPKW.setType("Toyota Aygo");
            currentPKW.setPropulsion("Verbrenner");
            currentPKW.setAvailable(false);
            currentPKW.setDayPrice(5);
        return currentPKW;
    }


    public  List<PKW> fillPKWListWithDummiwies(){
        testListPKW = new ArrayList<>();

        for (int counter = 0; counter < AMOUNT_OF_DUMMIES; counter++) {
            currentPKW = new PKW();
            currentPKW.setPKWId(counter);
            currentPKW.setType("Mercedes A" + counter*counter);
            currentPKW.setPropulsion("Elektric");
            currentPKW.setAvailable(false);
            currentPKW.setDayPrice(100*counter);
            testListPKW.add(currentPKW);
        }
        return testListPKW;
    }
    //endregion

    // Region 4. toString
    //endregion
}
