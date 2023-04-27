package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import ninemanmorris.screen.ScreenPage;

/**
 * Controller class for the result screen of the game
 */
public class ResultScreenController extends ScreenController {
    
    /**
     * Switch to the start screen of the game
     * @param event - ActionEvent that triggers the start of a new game
     * @throws IOException
     */
    public void switchTitleScreen(ActionEvent event) throws IOException {
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }
}
