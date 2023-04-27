package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

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

    @Override
    public String getMoveQuote() {
        return MoveQuote.REMOVE_PHASE.toString();
    }
}
