package ninemanmorris.gamelogic.game;

import ninemanmorris.gamelogic.IMorrisGameInputHandler;
import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.gamelogic.MorrisBoard;
import ninemanmorris.player.playertype.Player;

public abstract class MorrisGame implements IMorrisGameInputHandler {
    
    private MorrisBoard gameBoard;
    private Player[] players;
    private Player currentPlayerTurn;
    private IMorrisGameSubscriber[] subscribers;

    public MorrisGame(Player p1, Player p2) {

    }

    public void addSubscriber(IMorrisGameSubscriber subscriber) {

    }

    public void udpateSubscribers() {
        for (IMorrisGameSubscriber subscriber : subscribers) {
            subscriber.updateGameEnd(false);
        }
    }

    protected MorrisBoard getGameBoard() {
        return gameBoard;
    }

    protected Player[] getPlayers() {
        return players;
    }

    protected Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }
}
