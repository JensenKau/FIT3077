package ninemanmorris.Display;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String name;
    private Node node;

    public void switchScene(String name, Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
            System.out.println(loader.getLocation());
            root = loader.load();
            stage = (Stage) node.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToTitleScene(ActionEvent event) throws IOException {
        name = "/ninemanmorris/Display/title_screen.fxml";
        node = (Node)event.getSource();
        switchScene(name, node);
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        name = "/ninemanmorris/Game/game_screen.fxml";
        node = (Node) event.getSource();
        switchScene(name, node);
    }
}
