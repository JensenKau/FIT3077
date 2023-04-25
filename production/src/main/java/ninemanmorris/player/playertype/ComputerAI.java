package ninemanmorris.player.playertype;

import ninemanmorris.move.Move;

public class ComputerAI extends Player {

    private static final boolean IS_REQUIRE_INPUT = false;

    public ComputerAI(boolean isRed) {
        super(isRed);
    }

    @Override
    public boolean getIsRequireInput() {
        return IS_REQUIRE_INPUT;
    }
    
    @Override
    public Move getMove() {
        Move currentMove = super.getMove();

        // TODO: Need to generate random values here
        currentMove.setMovePosition(0, 0);

        return currentMove;
    }
}
