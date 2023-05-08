package ninemanmorris.player.playertype;

import ninemanmorris.move.movetype.Move;
import ninemanmorris.move.movetype.PlaceToken;

/**
 * Represents a player 
 */
public abstract class Player {
    
    private Move currentMove;
    private boolean isRed;

    /**
     * Player constructor for creating Player
     * @param isRed - true if the player is a red player, false otherwise
     */
    public Player(boolean isRed) {
        this.isRed = isRed;
        this.currentMove = new PlaceToken(isRed);
    }

    /**
     * Get if the this player if a red player
     * @return true if this player is a red player, false otherwise
     */
    public boolean getIsRed() {
        return isRed;
    }

    /**
     * Get the move that the player can make currently
     * @return The move that the player can make currently
     */
    public Move getMove() {
        return currentMove;
    }

    /**
     * Get if the player require user input
     * @return true if the player require user input, false otherwise
     */
    public abstract boolean getIsRequireInput();

    /**
     * Set the new move that the player can make
     * @param move - The new move that the player can make
     */
    public void setMove(Move move) {
        this.currentMove = move;
    }
}
