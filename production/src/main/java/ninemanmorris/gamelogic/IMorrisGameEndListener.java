package ninemanmorris.gamelogic;

public interface IMorrisGameEndListener {
    
    /**
     * This method will be called when the game ends
     * @param isRed - the player that won
     */
    void updateGameEnd(boolean isRed);

    /**
     * This method will be called when the game is a draw
     */
    void updateGameDraw();
}
