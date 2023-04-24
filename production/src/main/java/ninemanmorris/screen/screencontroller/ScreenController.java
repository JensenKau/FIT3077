package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ScreenController {

    private final static int WINDOW_WIDTH = 720;
    private final static int WINDOW_HEIGHT = 720;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
}
