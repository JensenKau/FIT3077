package ninemanmorris;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import ninemanmorris.screen.screencontroller.TitleScreenController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Media sound;

    private static MediaPlayer mediaPlayer;

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
        sound = new Media(getClass().getResource("/audio/bgmusic_short.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
              mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();

        Image icon = new Image("file:src/main/resources/img/9mm_token_blue.png");
        stage.setTitle("Nine Mens' Morris");
        stage.getIcons().add(icon);
        stage.setResizable(false);

        TitleScreenController controller = new TitleScreenController();
        controller.loadStartScreen(stage);
    }

}