package de.dlaan.dlmietwagenverwaltung.model;

/**
 * Zentrale Objekt dieser Verwaltung ist {@link PKW}. Anwendung handelt um <br>
 * Vermietung von nur PKWs
 */
public class PKW {
    // Region 0. Konstanten
    private static final String DEFAULT_VALUE_STRING = "NothingToSeeHere";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final double DEFAULT_VALUE_DOUBLE = 1d;
    private static final int DEFAULT_VALUE_INTEGER = 0;

    private final String SEPARATOR = ",";
    private final String SPACES = "     ";

    private final int POS_ATTRIBUTE_ONE = 0;
    private final int POS_ATTRIBUTE_TWO = 1;
    private final int POS_ATTRIBUTE_THREE = 2;
    private final int POS_ATTRIBUTE_FOUR = 3;
    private final int POS_ATTRIBUTE_FIVE = 4;

    //endregion

    // Region 1. Variabelen und Attributen
    private int iPKWId;
    private String strType;
    private String strPropulsion;
    private boolean bAvailable;
    private double dblDayPrice;

    //endregion

    // Region 2. Konstruktor
/*
* Einfache Konstruktor ohne Parameters
 */
    public PKW() {
        this.iPKWId = DEFAULT_VALUE_INTEGER;
        this.strType = DEFAULT_VALUE_STRING;
        this.strPropulsion = DEFAULT_VALUE_STRING;
        this.bAvailable = DEFAULT_VALUE_BOOLEAN;
        this.dblDayPrice = DEFAULT_VALUE_DOUBLE;
    }

    /**
     *
     * @param strType : Type PKW
     * @param strPropulsion : Verbrenner oder Elektrik
     * @param bAvailable : Sofort Verfügbar oder nicht
     * @param dblDayPrice : Preise pro Tag
     */
    public PKW(int iPKWId, String strType,  String strPropulsion, boolean bAvailable, double dblDayPrice) {
        this.iPKWId = iPKWId;
        this.strType = strType;
        this.strPropulsion = strPropulsion;
        this.bAvailable = bAvailable;
        this.dblDayPrice = dblDayPrice;
    }

    //endregion

    // Region 3. Getter und Setters

    /**
     * Auslesen Zeile von CsvDatei und Attributen in PKW Instanz speichern
     * @param strInputLineFile : String : Satz aus CsvDatei
     */
    public void setAttributesFromCsvFile(String strInputLineFile){
        String[] attributes = strInputLineFile.split(SEPARATOR);
        this.iPKWId = Integer.parseInt(attributes[POS_ATTRIBUTE_ONE]);
        this.strType = attributes[POS_ATTRIBUTE_TWO];
        this.strPropulsion = attributes[POS_ATTRIBUTE_THREE];
        this.bAvailable = Boolean.parseBoolean(attributes[POS_ATTRIBUTE_FOUR]);
        this.dblDayPrice = Double.parseDouble(attributes[POS_ATTRIBUTE_FIVE]);
    }

    /**
     * Zusammenbauen einer String von Attributen einer PKWInstanz um nach CsvDatei<br>
     * geschickt zu werden
     * @return : String : Satz mit Komma separierte attributen
     */
    public String getAttributesWithSeparatorForCsvFile(){
        String strCsvLine = getPKWId() + SEPARATOR +
                            getType() + SEPARATOR +
                            getPropulsion() + SEPARATOR +
                            isAvailable() + SEPARATOR +
                            getDayPrice() + "\n";
        return strCsvLine;
    }
    public String getNicePhrase(){
        return iPKWId + SPACES
                + strType + SPACES + "\n";
          //      + strPropulsion + SPACES
          //      + bAvailable + SPACES
          //      + dblDayPrice +
    }
    public String getFirstItems() {
        String strShortDescription = this.iPKWId
                + SPACES + this.strType;
     //           + SPACES + this.strPropulsion
      //          + SPACES + this.isAvailable()
      //          + SPACES + this.dblDayPrice;
        return strShortDescription;
    }

    public int getPKWId() {
        return iPKWId;
    }

    public void setPKWId(int iPKWId) {
        this.iPKWId = iPKWId;
    }

    public String getType() {
        return strType;
    }

    public void setType(String strType) {
        this.strType = strType;
    }

    public String getPropulsion() {
        return strPropulsion;
    }

    public void setPropulsion(String strPropulsion) {
        this.strPropulsion = strPropulsion;
    }

    public boolean isAvailable() {
        return bAvailable;
    }

    public void setAvailable(boolean bAvailable) {
        this.bAvailable = bAvailable;
    }

    public double getDayPrice() {
        return dblDayPrice;
    }

    public void setDayPrice(double dblDayPrice) {
        this.dblDayPrice = dblDayPrice;
    }

    //endregion

    // Region 4. toString

    @Override
    public String toString() {
        return "PKW{" +
                "iPKWId=" + iPKWId +
                ", strType='" + strType + '\'' +
                ", strPropulsion='" + strPropulsion + '\'' +
                ", bAvailable=" + bAvailable +
                ", dblDayPrice=" + dblDayPrice +
                '}';
    }
    //endregion
}
