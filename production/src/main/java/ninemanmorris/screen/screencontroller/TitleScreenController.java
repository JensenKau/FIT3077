package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ninemanmorris.gamelogic.MorrisGameFactory;
import ninemanmorris.player.PlayerType;
import ninemanmorris.screen.ScreenPage;

/**
 * Controller for the title screen of the game
 */
public class TitleScreenController extends ScreenController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button quitGameButton;

    /**
     * Load the start screen of the game
     * @param stage - Stage to load the start screen of the game on
     * @throws IOException
     */
    public void loadStartScreen(Stage stage) throws IOException {
        setStage(stage);
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }

    /**
     * Start a new game with two human players
     * @param event - ActionEvent that triggers the start of a new game
     * @throws IOException
     */
    public void startTwoPlayerGame(ActionEvent event) throws IOException {
        FXMLLoader loader = switchScene(ScreenPage.GAME_SCREEN.toString());
        GameScreenController controller = loader.getController();

        controller.setMorrisGame(MorrisGameFactory.createMorrisGame(PlayerType.HUMAN, PlayerType.HUMAN, controller));
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
