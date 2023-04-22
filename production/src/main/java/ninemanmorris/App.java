package ninemanmorris;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ninemanmorris.Controller.GameController;
import ninemanmorris.Controller.TitleScreenController;
import ninemanmorris.Enum.PageEnum;
import ninemanmorris.Manager.ScreenManager;

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

        TitleScreenController titleScreenController = new TitleScreenController();
        ScreenManager.getInstance().appendScreenController(PageEnum.TITLE_SCENE, titleScreenController);

        GameController gameController = new GameController();
        ScreenManager.getInstance().appendScreenController(PageEnum.GAME_SCENE, gameController);

        System.out.println(ScreenManager.getInstance().getEnumToScreenController());

        titleScreenController.getView(stage);
    }

}