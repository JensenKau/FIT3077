package ninemanmorris.gamelogic;

public class Position {
    
    private Position[] horizontalNeighbours;
    private Position[] verticalNeighbours;
    private Token token;

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
        Position[] neighbours = (isVertical) ? verticalNeighbours : horizontalNeighbours;

        for (int i = 0; i < neighbours.length; i++) {
            Position[] currentNeighbours = (isVertical) ? neighbours[i].getVerticalNeighbours() : neighbours[i].getHorizontalNeighbours();

            if (currentNeighbours.length == 2) {
                boolean checkNull = currentNeighbours[0].getToken() != null && neighbours[i] != null && currentNeighbours[1].getToken() != null;

                if (checkNull && currentNeighbours[0].getIsRedToken() == neighbours[i].getIsRedToken() == currentNeighbours[1].getIsRedToken()) {
                    return true;
                }
            }
        }

        if (neighbours.length == 2) {
            boolean checkNull = neighbours[0].getToken() != null && token != null && neighbours[1].getToken() != null;

            if (checkNull && neighbours[0].getIsRedToken() == this.getIsRedToken() == neighbours[1].getIsRedToken()) {
                return true;
            }
        }

        return false;
    }
}
