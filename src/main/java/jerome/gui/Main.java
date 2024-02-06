package jerome.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jerome.JeromeGpt;

/**
 * A GUI for Duke using FXML.
 * @@author se-edu
 * Reuse from https://se-education.org/guides/tutorials/javaFx.html
 * with minor modifications to cater for differences in
 * program design.
 */
public class Main extends Application {

    private JeromeGpt jeromeGpt = new JeromeGpt();

    /**
     * Setups the primary stage, loading the FXML file for the main window,
     * initializing UI and dataStorage, main window with the JeromeGpt instance.
     *
     * @param stage to be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            jeromeGpt.start();
            fxmlLoader.<MainWindow>getController().setJeromeGpt(jeromeGpt);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}