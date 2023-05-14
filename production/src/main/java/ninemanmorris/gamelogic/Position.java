package ninemanmorris.gamelogic;

/**
 * Represents a position on the 9 men's morris board
 */
public class Position {
    
    private Position[] horizontalNeighbours;
    private Position[] verticalNeighbours;
    private Token token;
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int[] getRowCol() {
        return new int[] {row, col};
    }

    /**
     * Get the token that is placed on the current position
     * @return The token that is placed on the current position
     */
    public Token getToken() {
        return token;
    }

    /**
     * Add a token to the current position
     * @param token - The token to be added
     */
    public void addToken(Token token) {
        this.token = token;
    }

    /**
     * Remove the token placed on the current position
     * @return The token to be removed
     */
    public Token removeToken() {
        Token currToken = token;

        token = null;

        return currToken;
    }

    /**
     * Get the vertical neighbours of this position
     * @return The vertical neighbours of this position
     */
    public Position[] getVerticalNeighbours() {
        return this.verticalNeighbours;
    }

    /**
     * Set the vertical neighbours of this position 
     * @param positions The vertical neighbours of this position
     */
    public void setVerticalNeighbours(Position[] positions) {
        this.verticalNeighbours = positions;
    }

    /**
     * Get the horizontal neighbours of this position
     * @return The horizontal neighbours of this position
     */
    public Position[] getHorizontalNeighbours() {
        return this.horizontalNeighbours;
    }

    /**
     * Set the horizontal neighbours of this position
     * @param positions The horizontal neighbours of this position
     */
    public void setHorizontalNeighbours(Position[] positions) {
        this.horizontalNeighbours = positions;
    }

    /**
     * Get if the current token is a red token
     * @return null if there is no token, true if red token, false otherwise
     */
    public Boolean getIsRedToken() {
        if (token == null) {
            return null;
        } 
        return token.getIsRedPlayer();
    }

    /**
     * Check if the current position is part of a mill
     * @return true if the current position if part of a mill, false otherwise
     */
    public boolean getIsMill() {
        return checkMill(true) || checkMill(false);
    }

    /**
     * Check either vertically or horizontally if the current position is part of a mill
     * @param isVertical - true to check for vertical mill, false to check for horizontal mill
     * @return true if there is a mill in the specified direction
     */
    public boolean checkMill(boolean isVertical) {
        Position currentPosition = this;
        Position[] neighbours = (isVertical) ? verticalNeighbours : horizontalNeighbours;

        if (neighbours.length == 1) {
            currentPosition = neighbours[0];
            
            if (isVertical) {
                neighbours = currentPosition.getVerticalNeighbours();
            } else {
                neighbours = currentPosition.getHorizontalNeighbours();
            }
        }

        boolean checkNull = neighbours[0].getToken() != null && neighbours[1].getToken() != null && currentPosition.getToken() != null;
        if (checkNull && neighbours[0].getIsRedToken() == neighbours[1].getIsRedToken() && neighbours[1].getIsRedToken() == currentPosition.getIsRedToken()) {
            return true;
        }

        return false;
    }
}
