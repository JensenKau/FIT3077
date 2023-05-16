package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import ninemanmorris.gamelogic.MorrisGameFactory;
import ninemanmorris.player.PlayerType;
import ninemanmorris.screen.ScreenPage;

public abstract class RestartScreenController extends ScreenController {

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
