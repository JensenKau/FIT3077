package ninemanmorris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
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
        try {
            // initialisation
            FXMLLoader loader = new FXMLLoader();
            FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/ninemanmorris/title_screen.fxml"));
            Parent root = loader.load(fileInputStream);
            Scene scene = new Scene(root, 720, 720);
            Image icon = new Image("file:src/main/resources/img/9mm_token_blue.png");

            // styling the stage
            stage.setTitle("Nine Mens' Morris");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}