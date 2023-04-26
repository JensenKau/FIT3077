package ninemanmorris.screen.screencontroller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    private IMorrisGameInputHandler morrisGame;
    private boolean isRedTurn;
    private Boolean[][] board;

    public void initialize() {
        
    }

    public void setMorrisGame(IMorrisGameInputHandler morrisGame) {
        this.morrisGame = morrisGame;
    }

    @Override
    public void update(boolean isRed, Boolean[][] board) {
        this.isRedTurn = isRed;
        this.board = board;

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

    public void gridClick(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();

        if (clickedNode != null) {
            int rowIndex = GridPane.getRowIndex(clickedNode);
            int colIndex = GridPane.getColumnIndex(clickedNode);
            System.out.println("clicked on: " + rowIndex + " " + colIndex);

            if (board[rowIndex][colIndex] != null && board[rowIndex][colIndex]) {
                System.out.println("executed");
                morrisGame.handleInput(rowIndex, colIndex);
            }
        }
    }
}