package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import ninemanmorris.screen.ScreenPage;

public class ResultScreenController extends ScreenController {
    
    public void switchTitleScreen(ActionEvent event) throws IOException {
        switchScene(ScreenPage.TITLE_SCREEN.toString());
    }
}
