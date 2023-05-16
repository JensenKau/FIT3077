package ninemanmorris.screen.screencontroller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import ninemanmorris.gamelogic.MorrisGameFactory;
import ninemanmorris.player.PlayerType;
import ninemanmorris.screen.ScreenPage;

public class SelectScreenController extends ScreenController {

  @FXML
  private Button player;

  @FXML
  private Button AI;

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

  /**
   * Start a new game with two human players
   * 
   * @param event - ActionEvent that triggers the start of a new game
   * @throws IOException
   */
  public void startTwoPlayerGame(ActionEvent event) throws IOException {
    FXMLLoader loader = switchScene(ScreenPage.GAME_SCREEN.toString());
    GameScreenController controller = loader.getController();

    controller.setMorrisGame(MorrisGameFactory.createMorrisGame(PlayerType.HUMAN, PlayerType.HUMAN, controller));
  }
}
