package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.shared.MoveType;

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
    public boolean[][] previewMove(Position[][] positions) {
        boolean[][] output = new boolean[positions.length][positions[0].length];

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                if (selectedPos == null) {
                    output[i][j] = positions[i][j] != null && positions[i][j].getToken() != null && positions[i][j].getIsRedToken() == getIsRedMove();

                } else {
                    output[i][j] = positions[i][j].getToken() == null && positions[i][j] != selectedPos;
                }
            }
        }

        return output; 
    }

    @Override
    public Move validateCurrentMove(Position[][] positions) {
        return this;
    }

    @Override
    public MoveType getMoveType() {
        return MoveType.FLY_PHASE;
    }

}
