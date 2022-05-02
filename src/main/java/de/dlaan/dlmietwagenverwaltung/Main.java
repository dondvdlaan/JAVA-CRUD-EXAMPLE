package de.dlaan.dlmietwagenverwaltung;

import de.dlaan.dlmietwagenverwaltung.settings.AppTexts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // Region 1. Konstanten
    public static final String LAYOUT_FXML = "View-Layout.fxml";
    public static final String PATH_CSS = "test.css";
    public static final int SCENE_HORIZONTAL = 600;
    public static final int SCENE_VERTICAL = 500;
    //endregion

    // Region 2. Stage und Scene Methode
    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(LAYOUT_FXML));
        Scene principalScene = new Scene(fxmlLoader.load(), SCENE_HORIZONTAL, SCENE_VERTICAL);

        // CSS Datei zufuegen
        principalScene.getStylesheets().add(Main.class.getResource(PATH_CSS).toExternalForm());

        mainStage.setTitle(AppTexts.APPLICATION_TEXT);
        mainStage.setScene(principalScene);
        mainStage.show();
    }
    //endregion

    // Region 3. Main
    public static void main(String[] args) {
        launch();
    }
}
    //endregion