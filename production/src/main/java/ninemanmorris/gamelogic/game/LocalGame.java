package ninemanmorris.gamelogic.game;

import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.player.playertype.Player;

/**
 * Represents a game that is hosted locally on the current device
 */
public class LocalGame extends MorrisGame {

    /**
     * LocalGame constructor
     * @param p1 - The first player (red player)
     * @param p2 - The second player (blue player)
     * @param subscriber - the subscribers that will be listening to this game
     */
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

            if (getGameBoard().getWinPlayer() != null) {
                delcareWinner(getGameBoard().getWinPlayer());
            } if (getGameBoard().getIsDrawGame()) {
                declareDraw();
            }

            if (getGameBoard().getSwitchTurn()) {
                switchPlayerTurn();
                getGameBoard().resetSwitchTurn();
                validatePlayerMove();
            }

        } while (!getCurrentPlayerTurn().getIsRequireInput());

        udpateSubscribers();
    }
}
