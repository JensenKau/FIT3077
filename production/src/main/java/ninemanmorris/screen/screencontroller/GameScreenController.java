package ninemanmorris.screen.screencontroller;

import java.util.Arrays;

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
import ninemanmorris.gamelogic.IMorrisGameInputHandler;
import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.screen.ScreenPage;

public class GameScreenController extends ScreenController implements IMorrisGameSubscriber {

    @FXML
    private GridPane grid;

    @FXML
    private Text turn;

    @FXML
    private Label red_token_count;

    @FXML
    private Label blue_token_count;

    private static final String RED_TOKEN_IMG = "/img/9mm_token_red.png";
    private static final String BLUE_TOKEN_IMG = "/img/9mm_token_blue.png";

    private IMorrisGameInputHandler morrisGame;
    private boolean isRedTurn;
    private Boolean[][] tokenBoard;

    public void initialize() {
        createGridClick();
    }

    public void setMorrisGame(IMorrisGameInputHandler morrisGame) {
        this.morrisGame = morrisGame;
    }

    @Override
    public void update(boolean isRed, Boolean[][] board) {
        this.isRedTurn = isRed;
        this.tokenBoard = board;

        // for (Node node : grid.getChildren()) {
        //     int rowIndex = GridPane.getRowIndex(node);
        //     int colIndex = GridPane.getColumnIndex(node);

        //     if (board[rowIndex][colIndex] != null) {
        //         Image token = null;
        //         ImageView imageView = null;

        //         if (board[rowIndex][colIndex]) {
        //             token = new Image(getClass().getResource(RED_TOKEN_IMG).toExternalForm());
        //         } else {
        //             token = new Image(getClass().getResource(BLUE_TOKEN_IMG).toExternalForm());
        //         }

        //         imageView = new ImageView(token);
        //         imageView.setFitWidth(40);
        //         imageView.setFitHeight(40);

        //         grid.getChildren().remove(node);
        //     }
        // }

        // grid.getChildren().remove(49, grid.getChildren().size());
        for (Node node : grid.getChildren()) {
            StackPane desiredStackPane = (StackPane) node;
            desiredStackPane.getChildren().clear();
        }
        
        
        EventHandler<MouseEvent> placingImageHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Node clickedNode = event.getPickResult().getIntersectedNode().getParent();

                if (clickedNode != null) {
                    int rowIndex = GridPane.getRowIndex(clickedNode);
                    int colIndex = GridPane.getColumnIndex(clickedNode);
                    System.out.println("clicked on: " + rowIndex + " " + colIndex);
                    morrisGame.handleInput(rowIndex, colIndex);
                }
            }
        };

        for (int i = 0; i < tokenBoard.length; i++) {
            for (int j = 0; j < tokenBoard[0].length; j++) {
                if (tokenBoard[i][j] != null) {
                    Image token = null;
                    ImageView imageView = null;

                    if (tokenBoard[i][j]) {
                        token = new Image(getClass().getResource(RED_TOKEN_IMG).toExternalForm());
                    } else {
                        token = new Image(getClass().getResource(BLUE_TOKEN_IMG).toExternalForm());
                    }

                    imageView = new ImageView(token);
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);

                    // grid.add(imageView, j, i);
                    for (Node node : grid.getChildren()) {
                        int row = GridPane.getRowIndex(node);
                        int column = GridPane.getColumnIndex(node);
                        if (row == i && column == j) {
                            StackPane desiredStackPane = (StackPane) node;
                            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, placingImageHandler);
                            desiredStackPane.getChildren().add(imageView);
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(tokenBoard));
        

        updatePlayerTurn();
    }

    private void updatePlayerTurn() {
        if (isRedTurn) {
            turn.setText("Red Turn");
        } else {
            turn.setText("Blue Turn");
        }
    }

    @Override
    public void updateGameEnd(boolean isRed) {
        try {
            switchScene(ScreenPage.RESULT_SCREEN.toString());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void createGridClick() {
        EventHandler<MouseEvent> placingHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Node clickedNode = event.getPickResult().getIntersectedNode();

                if (clickedNode != null) {
                    int rowIndex = GridPane.getRowIndex(clickedNode);
                    int colIndex = GridPane.getColumnIndex(clickedNode);
                    System.out.println("clicked on: " + rowIndex + " " + colIndex);

                    morrisGame.handleInput(rowIndex, colIndex);
                }
            }
        };

        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, placingHandler);
    }
}