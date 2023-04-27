package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.gamelogic.Token;

/**
 * Represents a specific type of move that the player can make in the 9 men's morris game,
 * where the player places a token on the board
 */
public class PlaceToken extends Move {

    private int tokenCount;

    /**
     * The PlaceToken constructor for creating a PlaceToken move
     * @param isRedMove - true if the move belongs to the red player, false otherwise
     */
    public PlaceToken(boolean isRedMove) {
        super(isRedMove);
        this.tokenCount = 9;
    }
    
    @Override
    public Move performMove(Position pos) {
        System.out.println(getIsRedMove() + " " + tokenCount);
        Move nextMove = null;
        boolean posHasToken = pos.getToken() != null;

        if (!posHasToken) {
            pos.addToken(new Token(getIsRedMove()));
            tokenCount -= 1;
        } else {
            nextMove = this;
        }

        if (tokenCount > 0) {
            nextMove = this;
        } else {
            nextMove = new AdjacentMove(getIsRedMove());
        }

        if (pos.getIsMill()) {
            nextMove = new RemoveToken(getIsRedMove(), nextMove);
        } else if (!posHasToken) {
            enableSwitchTurn();
        }

        return nextMove;
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
        return MoveQuote.PLACE_PHASE.toString();
    }

}
