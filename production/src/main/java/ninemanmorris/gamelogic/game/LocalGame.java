package ninemanmorris.gamelogic.game;

import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.player.playertype.Player;

public class LocalGame extends MorrisGame {

    public LocalGame(Player p1, Player p2, IMorrisGameSubscriber subscriber) {
        super(p1, p2, subscriber);
    }

    @Override
    public void handleInput(int row, int col) {
        do {
            Player currentPlayer = getCurrentPlayerTurn();

            if (!getCurrentPlayerTurn().getIsRequireInput()) {
                currentPlayer.setMove(getGameBoard().executeMove(currentPlayer.getMove()));
            } else {
                currentPlayer.setMove(getGameBoard().executeMove(currentPlayer.getMove(), row, col));
            }

            if (getGameBoard().getSwtichTurn()) {
                switchPlayerTurn();
                getGameBoard().resetSwitchTurn();
            }

        } while (!getCurrentPlayerTurn().getIsRequireInput());

        udpateSubscribers();
    }
}
