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

public class TitleScreenController extends ScreenController {

    @FXML
    private Button startGameButton;

    public void loadStartScreen(Stage stage) throws IOException {
        setStage(stage);
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }

    public void startTwoPlayerGame(ActionEvent event) throws IOException {
        FXMLLoader loader = switchScene(ScreenPage.GAME_SCREEN.toString());
        GameScreenController controller = loader.getController();

        controller.setMorrisGame(MorrisGameFactory.createMorrisGame(PlayerType.HUMAN, PlayerType.HUMAN));
    }

    // initialise a show pop up listener for instruction button

    // initialise a close game listener for quit game button
}
