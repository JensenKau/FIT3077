package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ninemanmorris.screen.ScreenPage;
import javafx.scene.text.Text;

/**
 * Controller class for the result screen of the game
 */
public class ResultScreenController extends RestartScreenController {
    
    private boolean redWon;

    @FXML
    private Text playerWonTxt;

    @FXML
    private Text counterTxt;

    /**
     * Switch to the start screen of the game
     * @param event - ActionEvent that triggers the start of a new game
     * @throws IOException
     */
    public void switchTitleScreen(ActionEvent event) throws IOException {
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }

    public void setRedWon(boolean redWon) {
        this.redWon = redWon;
        setPlayerWonTxt();
    }

    private void setPlayerWonTxt() {
        if (redWon) {
            playerWonTxt.setText("Player 1 Won!");
            // counterTxt.setText("1 - 0");
        } else {
            playerWonTxt.setText("Player 2 Won!");
            // counterTxt.setText("0 - 1");
        }
    }
}
