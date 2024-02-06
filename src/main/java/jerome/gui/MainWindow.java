package jerome.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jerome.JeromeGpt;

/**
 * Controller for MainWindow.
 * Provides the layout for the other controls.
 * @@author se-edu
 * Reuse from https://se-education.org/guides/tutorials/javaFx.html
 * with minor modifications to cater for differences in
 * program design.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private JeromeGpt jeromeGpt;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Ensures that the scrollPane automatically scrolls to the bottom
     * when new dialog boxes are added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets JeromeGpt instance for the MainWindow.
     * @param j the JeromeGpt instance to set
     */
    public void setJeromeGpt(JeromeGpt j) {
        this.jeromeGpt = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * JeromeGPT's reply and then appends them to
     * the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = jeromeGpt.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}