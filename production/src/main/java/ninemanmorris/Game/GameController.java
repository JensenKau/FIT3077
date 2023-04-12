package ninemanmorris.Game;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {
  
  @FXML
  private ImageView red_token;

  @FXML
  private ImageView blue_token;

  public void initialize() {
    Image redTokenImage = new Image(getClass().getResource("/img/9mm_token_red.png").toExternalForm());
    Image blueImageToken = new Image(getClass().getResource("/img/9mm_token_blue.png").toExternalForm());
    
    red_token.setImage(redTokenImage);
    blue_token.setImage(blueImageToken);
  }
}