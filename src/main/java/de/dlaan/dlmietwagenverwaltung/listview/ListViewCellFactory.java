package de.dlaan.dlmietwagenverwaltung.listview;

import de.dlaan.dlmietwagenverwaltung.model.PKW;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


/**
 * Handelt mit einer eigenen
 * Zelle den Aufbau und die Anzeige
 * der ListViewItems welche {@link PKW} enthalten
 */
public class ListViewCellFactory implements Callback<ListView<PKW>, ListCell<PKW>> {

    /**
     * Baut eine neues ListViewItem auf
     *
     * @param listViewItemCell :{@link ListView} - {@link PKW} : PKWsliste
     *
     * @return lvPKWCell : {@link ListCell} - {@link PKW}
     */
    @Override
    public ListCell<PKW> call(ListView<PKW> listViewItemCell) {
        return new ListViewCell();
    }
}