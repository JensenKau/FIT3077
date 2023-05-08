package ninemanmorris.player.playertype;

import java.util.Random;

import ninemanmorris.move.movetype.Move;

/**
 * Represents a Computer AI player
 */
public class ComputerAI extends Player {

    private static final boolean IS_REQUIRE_INPUT = false;
    private static final Random RAND_INSTANCE = new Random();

    private static final int BOARD_LENGTH = 7;
    private static final int BOARD_WIDTH = 7;

    /**
     * Constructor for ComputerAI
     * @param isRed - true if the current player is a red player, false otherwise
     */
    public ComputerAI(boolean isRed) {
        super(isRed);
    }

    @Override
    public boolean getIsRequireInput() {
        return IS_REQUIRE_INPUT;
    }
    
    /**
     * Insert random move positions and return the move 
     */
    @Override
    public Move getMove() {
        Move currentMove = super.getMove();

        currentMove.setMovePosition(RAND_INSTANCE.nextInt(BOARD_LENGTH), RAND_INSTANCE.nextInt(BOARD_WIDTH));

        return currentMove;
    }
}
