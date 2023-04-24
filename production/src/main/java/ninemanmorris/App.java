package ninemanmorris;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        
        Image icon = new Image("file:src/main/resources/img/9mm_token_blue.png");
        stage.setTitle("Nine Mens' Morris");
        stage.getIcons().add(icon);
        stage.setResizable(false);
    }

}