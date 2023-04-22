package ninemanmorris.Controller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import ninemanmorris.Model.BoardModel;
import ninemanmorris.Model.PlayerModel;

public class GameScreenController extends ScreenController{

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

  public GameScreenController() {
    super("/fxml/game_screen.fxml");
  }
  
  public void initialize() { 
    run(); // just runs the game when the view is loaded
  }

  // removing the light from the board
  public void removeLight(int x, int y) {
    for (Node node : grid.getChildren()) {
      if (GridPane.getRowIndex(node) == x && GridPane.getColumnIndex(node) == y) {
        StackPane desiredStackPane = (StackPane) node;
        desiredStackPane.getChildren().removeIf(node1 -> node1 instanceof Circle);
        break;
      }
    }
  }
  
  // removing all the lights from the board
  public void removeAllLight() {
    for (Node node : grid.getChildren()) {
      StackPane desiredStackPane = (StackPane) node;
      desiredStackPane.getChildren().removeIf(node1 -> node1 instanceof Circle);
    }
  }

  // lights up available spots in the map
  public void lightUpAvailableSpots() {
    EventHandler<MouseEvent> placingHandler = new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent e) {
        Node clickedNode = e.getPickResult().getIntersectedNode().getParent();
        if (clickedNode != null) {
          int colIndex = GridPane.getColumnIndex(clickedNode);
          int rowIndex = GridPane.getRowIndex(clickedNode);
          System.out.println("Clicked at " + colIndex + ", " + rowIndex);

          placeToken(rowIndex, colIndex, isPlayer1Turn); // also switches turns here

          if (player1.getTokenCount() == 0 && player2.getTokenCount() == 0) {
            removeAllLight();
            movingPhase();
          }
        }
      }
    };
    for (Node node : grid.getChildren()) {
      int row = GridPane.getRowIndex(node);
      int column = GridPane.getColumnIndex(node);
      if (isValidPosition(row, column) && isPositionEmpty(row, column)) {
        StackPane desiredStackPane = (StackPane) node;
        Circle circle = new Circle(20);
        circle.setStyle("-fx-fill: #7aee11;" +
        "-fx-cursor: hand;");
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, placingHandler);

        desiredStackPane.getChildren().add(circle);
      }
    }

  }

  public void run() {
    System.out.println("Game is running");
    lightUpAvailableSpots(); // also runs the game, cuz the lighted spots allow the player to place tokens
  }

  public void movingPhase() {
    // something here maybe?
  }

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
            StackPane desiredStackPane = (StackPane) node;
            desiredStackPane.getChildren().add(imageView);
            break;
          }
        }

        // remove the light which the token has been placed on 
        removeLight(x, y);

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
    currentPlayer.setTokenCount(currentPlayer.getTokenCount() - 1);
    red_token_count.setText("" + player1.getTokenCount());
    blue_token_count.setText("" + player2.getTokenCount());

    isPlayer1Turn = !isPlayer1Turn;
    currentPlayer = isPlayer1Turn ? player1 : player2;

    turn.setText("Turn: " + currentPlayer.getName());
  }
}