package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

public class RemoveToken extends Move {

    private Move previousMove;

    public RemoveToken(boolean isRedMove, Move previousMove) {
        super(isRedMove);
        this.previousMove = previousMove;
    }

    @Override
    public Move performMove(Position pos) {
        if (pos.getToken() != null && !pos.getIsMill() && pos.getIsRedToken() != getIsRedMove()) {
            pos.removeToken();
            enableSwitchTurn();
            return previousMove;
        }

        return this;
    }

    @Override
    public Position[] previewMove(Position[][] positions) {
        return null;
    }

    @Override
    public Move validateCurrentMove(Position[][] positions) {
        return this;
    }
}
