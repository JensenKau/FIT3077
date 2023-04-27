package ninemanmorris.move.movetype;

import ninemanmorris.gamelogic.Position;
import ninemanmorris.move.MoveQuote;

/**
 * Represents a specific type of move that the player can make in the 9 men's morris game,
 * where the player selects one token and places it to an adjacent position
 */
public class AdjacentMove extends Move {

    private Position selectedPos;

    /**
     * The AdjacentMove constructor for creating an AdjacentMove
     * @param isRedMove - true if the move belongs to a red player, false otherwiise
     */
    public AdjacentMove(boolean isRedMove) {
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
            if (pos == selectedPos) {
                output = this;

            } else if (pos.getToken() == null && (isNeighbour(selectedPos.getVerticalNeighbours(), pos) || isNeighbour(selectedPos.getHorizontalNeighbours(), pos))) {
                pos.addToken(selectedPos.removeToken());

                if (pos.getIsMill()) {
                    output = new RemoveToken(getIsRedMove(), new AdjacentMove(getIsRedMove()));
                } else {
                    enableSwitchTurn();
                    output = new AdjacentMove(getIsRedMove());
                }

            } else {
                output = new AdjacentMove(getIsRedMove());
            }
        }

        return output;
    }

    /**
     * Checks if the position is in the neighbour list 
     * @param neighbours - The list of neighbours
     * @param current - The position to compare with
     * @return true if the current position is in the neighbour list, false otherwise
     */
    private boolean isNeighbour(Position[] neighbours, Position current) {
        for (Position neighbour : neighbours) {
            if (current == neighbour) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Position[] previewMove(Position[][] positions) {
        return null;
    }

    @Override
    public Move validateCurrentMove(Position[][] positions) {
        int count = 0;

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                if (positions[i][j].getToken() != null && positions[i][j].getIsRedToken() == getIsRedMove()) {
                    count += 1;
                }
            }
        }

        if (count == 3) {
            return new FlyingMove(getIsRedMove());
        }
        return this;
    }

    @Override
    public String getMoveQuote() {
        return MoveQuote.MOVE_PHASE.toString();
    }
    
}