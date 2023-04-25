package ninemanmorris.move;

import ninemanmorris.gamelogic.Position;

public abstract class Move {
    
    public abstract Move performMove(Position pos);

    public abstract Position[] previewMove(Position[][] positions);

    public abstract boolean validateMove(Position position);
}
