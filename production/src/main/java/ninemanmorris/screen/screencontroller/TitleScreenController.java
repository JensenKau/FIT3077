package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ninemanmorris.screen.ScreenPage;

/**
 * Controller for the title screen of the game
 */
public class TitleScreenController extends ScreenController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button quitGameButton;

    @FXML
    private Button instructionButton;

    /**
     * Load the start screen of the game
     * @param stage - Stage to load the start screen of the game on
     * @throws IOException
     */
    public void loadStartScreen(Stage stage) throws IOException {
        setStage(stage);
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }

    public void selectPlayer(ActionEvent event) throws IOException {
        switchScene(ScreenPage.SELECT_SCREEN.toString());
    }

    public void displayInstruction(ActionEvent event) throws IOException {
        switchScene(ScreenPage.INSTRUCTION_SCREEN.toString());
    }

    /**
     * Quit the game 
     * @param event - ActionEvent that triggers the game quit
     */
    public void quitGame(ActionEvent event) {
        Stage stage = (Stage) quitGameButton.getScene().getWindow();
        stage.close();
    }

    // initialise a show pop up listener for instruction button

    // initialise a close game listener for quit game button
}
