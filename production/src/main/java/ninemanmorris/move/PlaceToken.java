package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.gamelogic.Token;

public class PlaceToken extends Move {

    private int tokenCount;

    public PlaceToken(boolean isRedMove) {
        super(isRedMove);
        this.tokenCount = 9;
    }
    
    @Override
    public Move performMove(Position pos) {
        System.out.println(getIsRedMove() + " " + tokenCount);
        Move nextMove = null;

        if (pos.getToken() == null) {
            pos.addToken(new Token(getIsRedMove()));
            tokenCount -= 1;
        } 

        if (tokenCount > 0) {
            nextMove = this;
        } else {
            nextMove = new AdjacentMove(getIsRedMove());
        }

        if (pos.getIsMill()) {
            nextMove = new RemoveToken(getIsRedMove(), nextMove);
        } else {
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
}
