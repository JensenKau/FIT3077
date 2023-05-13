package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.shared.MoveType;

/**
 * Represents a type of move that the player can make in the 9 men's morris game
 */
public abstract class Move {

    private int row;
    private int col;
    private boolean switchTurn;
    private boolean isRedMove;
    private boolean isDraw;

    /**
     * The move constructor to instantiate a Move object
     * @param isRedMove - true if this move belongs to the red player, false otherwise
     */
    public Move(boolean isRedMove) {
        this.isRedMove = isRedMove;
    }
    
    /**
     * Perform the current move and returns the next move that the player can make
     * @param pos - The position that the player has selected
     * @return The next move that the player can make
     */
    public abstract Move performMove(Position pos, Position[][] board);

    /**
     * Get a list of positions that can be selected for the current move
     * @param positions - The game board
     * @return The list of positions that can be selected for the current move
     */
    public abstract boolean[][] previewMove(Position[][] positions);

    /**
     * Validate if the current move is still valid
     * @param positions - The game board
     * @return Returns itself if the move is still valid, return a new move otherwise
     */
    public abstract Move validateCurrentMove(Position[][] positions);

    /**
     * Get the quote specific for this move
     * @return A string representation of the quote for this move
     */
    public abstract MoveType getMoveType();

    public Boolean getWinPlayer(Position[][] positions) {
        int redCount = 0;
        int blueCount = 0;

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                if (positions[i][j] != null && positions[i][j].getToken() != null) {
                    if (positions[i][j].getIsRedToken()) {
                        redCount += 1;
                    } else if (!positions[i][j].getIsRedToken()) {
                        blueCount += 1;
                    }
                }
            }
        }

        if (redCount >= 3 && blueCount >= 3) {
            return null;
        } else if (redCount < 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the row and col that has been selected for this move
     * @return The row and col that has been selected for this move
     */
    public int[] getMovePosition() {
        return new int[] {row, col};
    }

    /**
     * Pre-select the row and col for this move
     * @param row - The row on the board
     * @param col - The col on the board
     */
    public void setMovePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Get if the player should switch turn
     * @return true if the player should switch turn, false otherwise
     */
    public boolean getSwitchTurn() {
        return switchTurn;
    }

    /**
     * Reset the switch turn 
     */
    public void resetSwitchTurn() {
        this.switchTurn = false;
    }

    /**
     * Enable switching turn 
     */
    protected void enableSwitchTurn() {
        this.switchTurn = true;
    }

    /**
     * Get if the current move is a red move
     * @return true if the current move belonngs to red player, false otherwise
     */
    protected boolean getIsRedMove() {
        return this.isRedMove;
    }

    public boolean getIsDraw() {
        return isDraw;
    }

    protected void enableDraw() {
        isDraw = true;
    }
}
