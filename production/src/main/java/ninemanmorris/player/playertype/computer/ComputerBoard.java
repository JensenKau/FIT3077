package ninemanmorris.player.playertype.computer;

import java.util.ArrayList;
import java.util.List;

import ninemanmorris.gamelogic.IMorrisGameStateListener;
import ninemanmorris.shared.MoveType;

public class ComputerBoard implements IMorrisGameStateListener {

    private boolean[][] interactables;

    @Override
    public void update(boolean isRed, int redToken, int blueToken, Boolean[][] board, boolean[][] interactables, List<int[][]> mills, MoveType move, int[] selectedPos) {
        this.interactables = interactables;
    }

    public int[] selectPosition() {
        ArrayList<int[]> availablePositions = new ArrayList<>(30);

        for (int i = 0; i < interactables.length; i++) {
            for (int j = 0; j < interactables[i].length; j++) {
                if (interactables[i][j]) {
                    availablePositions.add(new int[] {i, j});
                }
            }
        }

        return availablePositions.get((int)(Math.random() * availablePositions.size()));
    }
}
