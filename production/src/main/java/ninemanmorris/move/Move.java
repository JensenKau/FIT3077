package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

public abstract class Move {

    private int x;
    private int y;
    
    public abstract Move performMove(Position pos);

    public abstract Position[] previewMove(Position[][] positions);

    public abstract boolean validateMove(Position position);

    public int[] getMovePosition() {
        return new int[] {x, y};
    }

    public void setMovePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
