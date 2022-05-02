module de.dlaan.dlmietwagenverwaltung {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens de.dlaan.dlmietwagenverwaltung to javafx.fxml;
    exports de.dlaan.dlmietwagenverwaltung;
}