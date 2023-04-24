package ninemanmorris.screen.screencontroller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ninemanmorris.Enum.PageEnum;
import ninemanmorris.Manager.ScreenManager;

public abstract class ScreenController {

    protected final String PAGE_FXML;

    public ScreenController(String pageFxml) {
        this.PAGE_FXML = pageFxml;
    }

    public Parent loadFxml(String screenName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screenName));
        Parent root = loader.load();
        return root;
    }

    public void getView(Stage stage) throws IOException{
        Parent root = loadFxml(PAGE_FXML);
        Scene scene = new Scene(root, 720, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void getView(Node node) throws IOException {
        Parent root = loadFxml(PAGE_FXML);
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchSceneHandler(Node node, PageEnum pageEnum) {
        try {
            ScreenController screenController = ScreenManager.getInstance().getEnumToScreenController().get(pageEnum);
            screenController.getView(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
