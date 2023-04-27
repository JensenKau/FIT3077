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

/**
 * Controller class for the game screen of the game
 */
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

    /**
     * Initialise the game
     */
    public void initialize() {
        createGridClick();
    }

    /**
     * Set a new game instance for the GameScreenController to keep track of actions and results 
     * before starting the game
     * @param morrisGame - The new game instance to set GameScreenController with
     */
    public void setMorrisGame(IMorrisGameInputHandler morrisGame) {
        this.morrisGame = morrisGame;
    }

    /**
     * Update the look of the board
     * @param isRed - determine it is red's turn or not
     * @param board - The current state of the morris board
     */
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
        
        updateTokenPlayer(board);
        updatePlayerTurn();
    }

    /**
     * Updates which player's turn it is
     */
    private void updatePlayerTurn() {
        if (isRedTurn) {
            turn.setText("Red's Turn");
        } else {
            turn.setText("Blue's Turn");
        }
    }

    /**
     * Updates the number of tokens left for each player
     * @param board - The current state of the morris board
     */
    private void updateTokenPlayer(Boolean[][] board) {
        int redCount = 0;
        int blueCount = 0;

        for (Boolean[] row : board) {
            for (Boolean col : row) {
                if (col != null) {
                    if (col) {
                        redCount++;
                    } else {
                        blueCount++;
                    }
                }
            }
        }

        red_token_count.setText(String.valueOf(9 - redCount));
        blue_token_count.setText(String.valueOf(9 - blueCount));
    }

    /**
     * Update that the game has ended
     * @param isRed - determine it is red's turn or not
     */
    @Override
    public void updateGameEnd(boolean isRed) {
        try {
            switchScene(ScreenPage.RESULT_SCREEN.toString());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Initialise clickable grid for the game
     */
    public void createGridClick() {
        EventHandler<MouseEvent> placingHandler = new EventHandler<MouseEvent>() {

            /**
             * Determines what happens when the grid is clicked
             * @param event - MouseEvent that triggers when a grid is clicked
             */
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