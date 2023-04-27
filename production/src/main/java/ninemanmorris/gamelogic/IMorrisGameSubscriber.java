package ninemanmorris.gamelogic;

/**
 * An interface for allowing the classes to be notfied about changes from morrisgame
 */
public interface IMorrisGameSubscriber {

    /**
     * This method will be called whenever a board is in a new state
     * @param isRed - the current player turn
     * @param board - the board of tokens, where true represents red, false represents blue and null represents no token
     */
    void update(boolean isRed, Boolean[][] board);

    /**
     * This method will be called when the game ends
     * @param isRed - the player that won
     */
    void updateGameEnd(boolean isRed);
}
