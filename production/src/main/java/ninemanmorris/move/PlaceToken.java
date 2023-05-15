package ninemanmorris.move;

import java.util.List;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.gamelogic.Token;
import ninemanmorris.shared.MoveType;

/**
 * Represents a specific type of move that the player can make in the 9 men's morris game,
 * where the player places a token on the board
 */
public class PlaceToken extends Move {

    private List<Token> tokens;

    /**
     * The PlaceToken constructor for creating a PlaceToken move
     * @param isRedMove - true if the move belongs to the red player, false otherwise
     */
    public PlaceToken(boolean isRedMove, List<Token> tokens) {
        super(isRedMove);
        this.tokens = tokens;
    }
    
    @Override
    public Move performMove(Position pos, Position[][] board) {
        Move nextMove = null;
        boolean posHasToken = pos.getToken() != null;

        if (pos.getToken() == null) {
            pos.addToken(tokens.remove(tokens.size() - 1));
        } 

        if (tokens.size() > 0) {
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
    public boolean[][] previewMove(Position[][] positions) {
        boolean[][] output = new boolean[positions.length][positions[0].length];

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                output[i][j] = positions[i][j] != null && positions[i][j].getToken() == null;
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
        return MoveType.PLACE_PHASE;
    }

    @Override
    public Boolean getWinPlayer(Position[][] positions) {
        return null;
    }
}
