package ninemanmorris.player.playertype;

import ninemanmorris.move.Move;
import ninemanmorris.move.PlaceToken;

public abstract class Player {
    
    private Move currentMove;
    private boolean isRed;

    public Player(boolean isRed) {
        this.isRed = isRed;
        this.currentMove = new PlaceToken(isRed);
    }

    public boolean getIsRed() {
        return isRed;
    }

    public Move getMove() {
        return currentMove;
    }

    public abstract boolean getIsRequireInput();

    public void setMove(Move move) {
        this.currentMove = move;
    }
}
