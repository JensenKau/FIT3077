package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ninemanmorris.screen.ScreenPage;

public class SelectScreenController extends RestartScreenController {

  @FXML
  private Button player;

  @FXML
  private Button AI;

  // for now there is no method, this class will later be used to determine if the player wishes to play with computer or player
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
