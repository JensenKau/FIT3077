package ninemanmorris;

import java.io.File;
import java.io.FileInputStream;
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
    private String pathname;
    private Node node;

    public void switchScene(String pathname, Node node) {
        try {
            FXMLLoader loader = new FXMLLoader();
            FileInputStream fileInputStream = new FileInputStream(new File(pathname));
            root = loader.load(fileInputStream);
            stage = (Stage)node.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToTitleScene(ActionEvent event) throws IOException {
        pathname = "src/main/resources/ninemanmorris/title_screen.fxml";
        node = (Node)event.getSource();
        switchScene(pathname, node);
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        pathname = "src/main/resources/ninemanmorris/game_screen.fxml";
        node = (Node)event.getSource();
        switchScene(pathname, node);
    }
}
