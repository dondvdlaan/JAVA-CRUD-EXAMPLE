package de.dlaan.dlmietwagenverwaltung.listview;

import de.dlaan.dlmietwagenverwaltung.model.PKW;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Paint;

/**
 * Repreasentiert eine Zelle die {@link PKW} enthaelt,welche
 * aufgebaut wird und die aktualisierung
 * des Inhalts vornimmt
 */
public class ListViewCell extends ListCell<PKW> {
    // Region 0. Konstanten
    public static final String FX_BACKGROUND_IS_AVAILABLE = "-fx-background-color: MEDIUMSPRINGGREEN;-fx-font-size: 14.0;";
    public static final String FX_BACKGROUND_IS_NOT_AVAILABLE = "-fx-background-color: ANTIQUEWHITE; -fx-font-size: 14.0;";
    public static final String TEXT_COLOR = "NAVY";

    //endregion

    // Region 1. Variabelen und Attributen
    //endregion

    // Region 2. Konstruktor
    // Nicht zutreffend hier, weil updateItem von ListView aufgerufen wird
    //endregion

    // Region 3. Methoden
    /**
     * Zeigt entweder nichts an oder den {@link PKW} der mitgeliefert wird.
     * Dieser kommt von der {@link javafx.scene.control.ListView} selbst1
     *
     * @param cellToShow : {@link PKW} : PKW das angezeigt werden soll
     * @param empty           :  boolean : gibt an ob die Zelle / ListViewItem leer sein soll.
     */
    @Override
    protected void updateItem(PKW cellToShow, boolean empty) {
        super.updateItem(cellToShow, empty);

        if (empty || cellToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            if(cellToShow.isAvailable()){
                this.setTextFill(Paint.valueOf(TEXT_COLOR));
                setStyle(FX_BACKGROUND_IS_AVAILABLE);
            }else{
                this.setTextFill(Paint.valueOf(TEXT_COLOR));
                setStyle(FX_BACKGROUND_IS_NOT_AVAILABLE);
            }
            setText(cellToShow.getNicePhrase());
        }
    }
    //endregion
}