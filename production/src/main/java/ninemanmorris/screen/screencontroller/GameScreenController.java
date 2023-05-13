package ninemanmorris.screen.screencontroller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ninemanmorris.gamelogic.IMorrisGameInputHandler;
import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.screen.ScreenPage;
import ninemanmorris.shared.MoveType;

/**
 * Controller class for the game screen of the game
 */
public class GameScreenController extends ScreenController implements IMorrisGameSubscriber, IInputHandler {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private GridPane grid;

    @FXML
    private Text turn;

    @FXML
    private Label red_token_count;

    @FXML
    private Label blue_token_count;

    @FXML
    private Text move_quote;

    private IMorrisGameInputHandler morrisGame;
    private boolean isRedTurn;

    private GameScreenGrid gameGrid;

    /**
     * Initialise the game
     */
    public void initialize() {
        this.gameGrid = new GameScreenGrid(grid, mainPane, this);
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
     * @param moveQuote - determine the quote of the move to be displayed
     */
    @Override
    public void update(boolean isRed, int redToken, int blueToken, Boolean[][] board, boolean[][] interactables, List<int[][]> mills, MoveType move) {
        this.isRedTurn = isRed;

        gameGrid.updateAll(board, interactables, mills);

        updatePlayerTurn();
        updateMoveQuote(move);
        updatePlayerToken(redToken, blueToken);
    }

    private void updatePlayerToken(int redToken, int blueToken) {
        red_token_count.setText(redToken + "");
        blue_token_count.setText(blueToken + "");
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
    private void updateMoveQuote(MoveType move) {
        String newQuote = "";

        if (move == MoveType.PLACE_PHASE) {
            newQuote = "Place your tokens on the board";
        } else if (move == MoveType.MOVE_PHASE) {
            newQuote = "Move your tokens to any empty adjacent spot";
        } else if (move == MoveType.FLY_PHASE) {
            newQuote = "Move your tokens to any empty spot";
        } else if (move == MoveType.REMOVE_PHASE) {
            newQuote = "Remove one of your opponent's token";
        } 

        move_quote.setText(newQuote);
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

    @Override
    public void handleInput(int row, int col) {
        morrisGame.handleInput(row, col);
    }
}