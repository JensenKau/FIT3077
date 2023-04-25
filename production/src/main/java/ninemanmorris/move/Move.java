package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

public abstract class Move {

    private int row;
    private int col;
    private boolean switchTurn;
    private boolean isRedMove;

    public Move(boolean isRedMove) {
        this.isRedMove = isRedMove;
    }
    
    public abstract Move performMove(Position pos);

    public abstract Position[] previewMove(Position[][] positions);

    public abstract Move validateCurrentMove(Position[][] positions);

    public int[] getMovePosition() {
        return new int[] {row, col};
    }

    public void setMovePosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean getSwitchTurn() {
        return switchTurn;
    }

    public void resetSwitchTurn() {
        this.switchTurn = false;
    }

    protected void enableSwitchTurn() {
        this.switchTurn = true;
    }

    protected boolean getIsRedMove() {
        return this.isRedMove;
    }
}
