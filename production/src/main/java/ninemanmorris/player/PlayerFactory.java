package ninemanmorris.player;

import ninemanmorris.player.playertype.*;

public class PlayerFactory {

    private PlayerFactory() {

    }

    public static Player createPlayer(PlayerType playerType, boolean isRed) {
        if (playerType == PlayerType.HUMAN) {
            return new HumanPlayer(isRed);
        } else if (playerType == PlayerType.COMPUTER) {
            return new ComputerAI(isRed);
        }

        return null;
    }
}
