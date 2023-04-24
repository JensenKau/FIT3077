package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ninemanmorris.screen.ScreenPage;

public class TitleScreenController extends ScreenController {

    @FXML
    private Button startGameButton;

    public void startTwoPlayerGame(ActionEvent event) throws IOException {
        switchScene(ScreenPage.GAME_SCREEN.toString());
    }

    // initialise a show pop up listener for instruction button

    // initialise a close game listener for quit game button
}
