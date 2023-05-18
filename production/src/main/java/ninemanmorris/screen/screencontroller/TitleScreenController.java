package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ninemanmorris.screen.ScreenPage;
import ninemanmorris.screen.screencontroller.gamescreen.GameMode;
import javafx.scene.layout.VBox;

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

    @FXML
    private VBox instructionScreen;

    @FXML
    private VBox selectScreen;

    public void initialize() {
        switchNodeVisibility(instructionScreen, false);
        switchNodeVisibility(selectScreen, false);
    }   

    /**
     * Load the start screen of the game
     * @param stage - Stage to load the start screen of the game on
     * @throws IOException
     */
    public void loadStartScreen(Stage stage) throws IOException {
        setStage(stage);
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }

    public void startTwoPlayerGame(ActionEvent event) throws IOException {
        Intent intent = new Intent();
        intent.addItems("Game Mode", GameMode.TWO_PLAYER_MODE);

        switchScene(ScreenPage.GAME_SCREEN.toString(), intent);
    }

    public void selectPlayer(ActionEvent event) throws IOException {
        switchNodeVisibility(selectScreen, true);
    }

    public void displayInstruction(ActionEvent event) throws IOException {
        switchNodeVisibility(instructionScreen, true);
    }

    /**
     * Quit the game 
     * @param event - ActionEvent that triggers the game quit
     */
    public void quitGame(ActionEvent event) {
        Stage stage = (Stage) quitGameButton.getScene().getWindow();
        stage.close();
    }

    public void closeInstructionScreen(ActionEvent event) throws IOException {
        switchNodeVisibility(instructionScreen, false);
    }

    public void closeSelectScreen(ActionEvent event) throws IOException {
        switchNodeVisibility(selectScreen, false);
    }
}
