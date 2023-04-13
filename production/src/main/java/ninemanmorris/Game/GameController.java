package ninemanmorris.Game;

import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import ninemanmorris.Model.BoardModel;
import ninemanmorris.Model.PlayerModel;

public class GameController {

  @FXML
  private ImageView red_token;

  @FXML
  private ImageView blue_token;

  @FXML
  private GridPane grid;

  @FXML
  private Text turn;

  @FXML
  private Label red_token_count;

  @FXML
  private Label blue_token_count;

  private BoardModel board = new BoardModel();
  private boolean isPlayer1Turn = true;
  private PlayerModel player1 = new PlayerModel("Player 1", true);
  private PlayerModel player2 = new PlayerModel("Player 2", false);
  private PlayerModel currentPlayer = player1;

  public void initialize() {
    Image redTokenImage = new Image(getClass().getResource("/img/9mm_token_red.png").toExternalForm());
    Image blueImageToken = new Image(getClass().getResource("/img/9mm_token_blue.png").toExternalForm());

    red_token.setImage(redTokenImage);
    blue_token.setImage(blueImageToken);

    run();
  }

  public void placingPhase() {
    EventHandler<MouseEvent> placingHandler = new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent e) {
        Node clickedNode = e.getPickResult().getIntersectedNode();
        if (clickedNode != null) {

          int colIndex = GridPane.getColumnIndex(clickedNode);
          int rowIndex = GridPane.getRowIndex(clickedNode);
          System.out.println("Clicked at " + colIndex + ", " + rowIndex);

          placeToken(rowIndex, colIndex, isPlayer1Turn); // also switches turns here

          if (player1.getTokenCount() == 0 && player2.getTokenCount() == 0) {
            grid.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            movingPhase();
          }
        }
      }
    };

    grid.addEventHandler(MouseEvent.MOUSE_CLICKED, placingHandler);
  }

  public void run() {
    System.out.println("Game is running");
    placingPhase();
  }

  public void movingPhase() {
    // something here maybe?
  }

  // placing phase
  // while (placingTimes < 9) {
  // // player1.placeToken();
  // // player2.placeToken();
  // }

  // while (placingPhase < 9) {
  // grid.setOnMouseClicked(event -> {
  // Node clickedNode = event.getPickResult().getIntersectedNode();

  // if (clickedNode != null) {

  // turn.setText("Turn: " + player1.getName());
  // int colIndex = GridPane.getColumnIndex(clickedNode);
  // int rowIndex = GridPane.getRowIndex(clickedNode);
  // System.out.println("Clicked at " + colIndex + ", " + rowIndex);
  // }

  // });
  // }
  // grid.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
  // placing phase

  // placing token on the board
  public void placeToken(int x, int y, boolean isRed) {

    // check if valid position
    if (isValidPosition(x, y)) {

      // check if the position is empty
      if (isPositionEmpty(x, y)) {

        Image image;
        ImageView imageView;

        if (isRed) {
          board.getPosition(x, y).setIsOccupiedByRed(true);
          image = new Image(getClass().getResource("/img/9mm_token_red.png").toExternalForm());
        } else {
          board.getPosition(x, y).setIsOccupiedByBlue(true);
          image = new Image(getClass().getResource("/img/9mm_token_blue.png").toExternalForm());
        }
        imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        // getting the stack pane on the grid and adding the image onto it
        for (Node node : grid.getChildren()) {
          if (GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) {
            if (node instanceof StackPane) {
              StackPane desiredStackPane = (StackPane) node;
              desiredStackPane.getChildren().add(imageView);
            }
            break;
          }
        }
        currentPlayer.setTokenCount(currentPlayer.getTokenCount() - 1);
        switchTurns();
      } else {
        System.out.println("Position is not empty");
      }
    } else {
      System.out.println("Invalid position");
    }
  }

  // checking if the position is empty (not too sure if this method should be
  // here, but I think it should cuz the Board shouldn't be holding any data logic
  // like this)
  public boolean isPositionEmpty(int x, int y) {
    return !board.getPosition(x, y).getIsOccupiedByBlue() && !board.getPosition(x, y).getIsOccupiedByRed();
  }

  public boolean isValidPosition(int x, int y) {
    return board.getPosition(x, y) != null;
  }

  public void switchTurns() {
    red_token_count.setText("" + player1.getTokenCount());
    blue_token_count.setText("" + player2.getTokenCount());

    isPlayer1Turn = !isPlayer1Turn;
    currentPlayer = isPlayer1Turn ? player1 : player2;

    turn.setText("Turn: " + currentPlayer.getName());
  }
}