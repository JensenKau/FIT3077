package ninemanmorris.gamelogic;

import java.util.List;

import ninemanmorris.shared.MoveType;

/**
 * An interface for allowing the classes to be notfied about changes from morrisgame
 */
public interface IMorrisGameSubscriber {

    /**
     * This method will be called whenever a board is in a new state
     * @param isRed - the current player turn
     * @param board - the board of tokens, where true represents red, false represents blue and null represents no token
     * @param moveQuote - quote for the current move to be displayed on the GUI
     */
    void update(boolean isRed, int redToken, int blueToken, Boolean[][] board, boolean[][] interactables, List<int[][]> mills, MoveType move, int[] selectedPos);

    /**
     * This method will be called when the game ends
     * @param isRed - the player that won
     */
    void updateGameEnd(boolean isRed);

    void updateGameDraw();
}
