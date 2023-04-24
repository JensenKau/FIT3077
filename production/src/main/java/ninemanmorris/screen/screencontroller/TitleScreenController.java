package ninemanmorris.screen.screencontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import ninemanmorris.Enum.PageEnum;

public class TitleScreenController extends ScreenController{

    @FXML
    private Button startGameButton;

    public TitleScreenController() {
        super("/fxml/title_screen.fxml");
    }

    public void startGameHandler(ActionEvent event) {
        Node node = (Node) event.getSource();
        super.switchSceneHandler(node, PageEnum.GAME_SCENE);
    }

    // initialise a show pop up listener for instruction button

    // initialise a close game listener for quit game button
}
