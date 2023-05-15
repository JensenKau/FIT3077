package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ninemanmorris.screen.ScreenPage;
import javafx.scene.control.Button;

/**
 * Controller class for the instruction screen of the game
 */
public class InstructionScreenController extends ScreenController {
    
    @FXML
    private Button cancelButton;

    /**
     * Switch to the start screen of the game
     * @param event - ActionEvent that triggers the start of a new game
     * @throws IOException
     */
    public void switchTitleScreen(ActionEvent event) throws IOException {
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }
}
