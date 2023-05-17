package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ninemanmorris.gamelogic.MorrisGameFactory;
import ninemanmorris.player.PlayerType;
import ninemanmorris.screen.ScreenPage;
import ninemanmorris.screen.screencontroller.gamescreen.GameScreenController;

/**
 * Abstract class for screen controllers
 */
public abstract class ScreenController {

    private final static int WINDOW_WIDTH = 860;
    private final static int WINDOW_HEIGHT = 720;

    private Stage stage;

    /**
     * Initialises the stage attribute of the controller with the stage passed in as a parameter
     * @param stage - a stage instance
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Main method for switching from one scene to another
     * @param name file directory to switch the scene to
     * @return FXMLLoader instance 
     * @throws IOException
     */
    public FXMLLoader switchScene(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
        Parent root = loader.load();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        ScreenController controller = loader.getController();

        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();

        return loader;
    }
    
    public void startTwoPlayerGame(ActionEvent event) throws IOException {
        FXMLLoader loader = switchScene(ScreenPage.GAME_SCREEN.toString());
        GameScreenController controller = loader.getController();

        controller.setMorrisGame(MorrisGameFactory.createMorrisGame(PlayerType.HUMAN, PlayerType.HUMAN, controller));
    }

    public void switchNodeVisibility(Pane node, boolean visible) {
        node.setVisible(visible);
        for (Node child : node.getChildren()) {
            child.setVisible(visible);
        }
    }

}
