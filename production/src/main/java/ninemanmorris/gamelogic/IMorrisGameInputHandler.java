package ninemanmorris.gamelogic;

/**
 * An interface for allowing classes to insert input into morrisgame
 */
public interface IMorrisGameInputHandler {
    
    /**
     * Insert input from user into morris game
     * @param row - The row that the player selected
     * @param col - The col that the player selected
     */
    void handleInput(int row, int col);
}
