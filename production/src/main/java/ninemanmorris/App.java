package ninemanmorris;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ninemanmorris.screen.screencontroller.TitleScreenController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    /**
     * Starts the game application
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Callback method invoked when the game has launched
     * @param stage - A Stage instance for the game GUI
     */
    @Override
    public void start(Stage stage) throws IOException {
        
        Image icon = new Image("file:src/main/resources/img/9mm_token_blue.png");
        stage.setTitle("Nine Mens' Morris");
        stage.getIcons().add(icon);
        stage.setResizable(false);

        TitleScreenController controller = new TitleScreenController();
        controller.loadStartScreen(stage);
    }

}