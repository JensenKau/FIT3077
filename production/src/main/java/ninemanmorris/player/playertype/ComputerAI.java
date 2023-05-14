package ninemanmorris.player.playertype;

import java.util.Random;

import ninemanmorris.move.Move;

/**
 * Represents a Computer AI player
 */
public class ComputerAI extends Player {

    private static final boolean IS_REQUIRE_INPUT = false;
    private static final Random RAND_INSTANCE = new Random();

    private static final int[][] AVAILABLE_POS = new int[][] {
        {0, 0}, {0, 3}, {0, 6},
        {1, 1}, {1, 3}, {1, 5},
        {2, 2}, {2, 3}, {2, 4},
        {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5}, {3, 6},
        {4, 2}, {4, 3}, {4, 4},
        {5, 1}, {5, 3}, {5, 5},
        {6, 0}, {6, 3}, {6, 6},
    };

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
        int[] randPos = AVAILABLE_POS[RAND_INSTANCE.nextInt(AVAILABLE_POS.length)];

        currentMove.setMovePosition(randPos[0], randPos[1]);

        return currentMove;
    }
}
