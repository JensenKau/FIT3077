package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

public class PlaceToken extends Move {

    private int tokenCount;

    public PlaceToken(boolean isRedMove) {
        super(isRedMove);
        this.tokenCount = 9;
    }

    @Override
    public Move performMove(Position pos) {
        return null;
    }

    @Override
    public Position[] previewMove(Position[][] positions) {
        return null;
    }
}
