package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.shared.MoveType;

/**
 * Represents a specific type of move that the player can make in the 9 men's morris game,
 * where the player removes a token on the board
 */
public class RemoveToken extends Move {

    private Move previousMove;

    /**
     * The RemoveToken contructor for creating a RemoveToken move
     * @param isRedMove - true if the move belongs to the red player, false otherwise
     * @param previousMove - the next move that the player can perform after performing this move
     */
    public RemoveToken(boolean isRedMove, Move previousMove) {
        super(isRedMove);
        this.previousMove = previousMove;
    }

    private boolean checkIsNoneAvailable(Position[][] positions) {
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                if (positions[i][j] != null && positions[i][j].getToken() != null && positions[i][j].getIsRedToken() != getIsRedMove() && !positions[i][j].getIsMill()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Move performMove(Position pos, Position[][] board) {
        boolean isNoneAvailable = checkIsNoneAvailable(board);

        if (isNoneAvailable && pos.getToken() != null && pos.getIsRedToken() != getIsRedMove()) {
            pos.removeToken();
            enableSwitchTurn();
            return previousMove;

        } else if (!isNoneAvailable && pos.getToken() != null && !pos.getIsMill() && pos.getIsRedToken() != getIsRedMove()) {
            pos.removeToken();
            enableSwitchTurn();
            return previousMove;
        }

        return this;
    }

    @Override
    public boolean[][] previewMove(Position[][] positions) {
        boolean[][] output = new boolean[positions.length][positions[0].length];
        boolean isNoneAvailable = checkIsNoneAvailable(positions);

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                if (isNoneAvailable) {
                    output[i][j] = positions[i][j] != null && positions[i][j].getToken() != null && positions[i][j].getIsRedToken() != getIsRedMove();

                } else {
                    output[i][j] = positions[i][j] != null && positions[i][j].getToken() != null && positions[i][j].getIsRedToken() != getIsRedMove() && !positions[i][j].getIsMill();
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
        return MoveType.REMOVE_PHASE;
    }

    @Override
    public Boolean getWinPlayer(Position[][] positions) {
        if (previousMove.getMoveType() == MoveType.PLACE_PHASE) {
            return null;
        }
        return super.getWinPlayer(positions);
    }
}
