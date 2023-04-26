package ninemanmorris.gamelogic;

public class Position {
    
    private Position[] horizontalNeighbours;
    private Position[] verticalNeighbours;
    private Token token;

    public int row;
    public int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Token getToken() {
        return token;
    }

    public void addToken(Token token) {
        this.token = token;
    }

    public Token removeToken() {
        Token currToken = token;

        token = null;

        return currToken;
    }

    public Position[] getVerticalNeighbours() {
        return this.verticalNeighbours;
    }

    public void setVerticalNeighbours(Position[] positions) {
        this.verticalNeighbours = positions;
    }

    public Position[] getHorizontalNeighbours() {
        return this.horizontalNeighbours;
    }

    public void setHorizontalNeighbours(Position[] positions) {
        this.horizontalNeighbours = positions;
    }

    public Boolean getIsRedToken() {
        if (token == null) {
            return null;
        } 
        return token.getIsRedPlayer();
    }

    public boolean getIsMill() {
        return checkMill(true) || checkMill(false);
    }

    // FIXME: possibly have errors (neighbour is part of a mill but self is not)
    private boolean checkMill(boolean isVertical) {
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
