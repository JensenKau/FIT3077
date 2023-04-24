package ninemanmorris.player;

import ninemanmorris.player.playertype.ComputerAI;
import ninemanmorris.player.playertype.HumanPlayer;
import ninemanmorris.player.playertype.Player;

public class PlayerFactory {

    private PlayerFactory() {

    }

    public static Player createPlayer(PlayerType playerType) {
        if (playerType == PlayerType.HUMAN) {
            return new HumanPlayer();
        } else if (playerType == PlayerType.COMPUTER) {
            return new ComputerAI();
        }

        return null;
    }
}
