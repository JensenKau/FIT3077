package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

/**
 * Represents a specific type of move that the player can make in the 9 men's morris game,
 * where the player selects a token and places it into any empty position on the board
 */
public class FlyingMove extends Move {

    private Position selectedPos;

    /**
     * The constructor for creating a FlyingMove
     * @param isRedMove - true if the move belongs to the red player, false otherwise
     */
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
            } else {
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
                    enableSwitchTurn();
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
