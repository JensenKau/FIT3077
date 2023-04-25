package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

public class FlyingMove extends Move {

    private Position selectedPos;

    public FlyingMove(boolean isRedMove) {
        super(isRedMove);
    }

    @Override
    public Move performMove(Position pos) {
        Move output = null;

        if (selectedPos == null) {
            if (pos.getToken() != null && pos.getIsRedToken() == getIsRedMove()) {
                selectedPos = pos;
                output = this;
            }

        } else {
            if (selectedPos == pos) {
                output = this;

            } else if (pos.getToken() == null) {
                pos.addToken(pos.removeToken());

                if (pos.getIsMill()) {
                    output = new RemoveToken(getIsRedMove(), new FlyingMove(getIsRedMove()));
                } else {
                    output = new FlyingMove(getIsRedMove());
                }

            } else {
                output = new FlyingMove(getIsRedMove());
            }
        }

        return output;
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
