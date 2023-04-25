package ninemanmorris.gamelogic.game;

import java.util.ArrayList;

import ninemanmorris.gamelogic.IMorrisGameInputHandler;
import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.gamelogic.MorrisBoard;
import ninemanmorris.player.playertype.Player;

public abstract class MorrisGame implements IMorrisGameInputHandler {
    
    private MorrisBoard gameBoard;
    private Player[] players;
    private Player currentPlayerTurn;
    private ArrayList<IMorrisGameSubscriber> subscribers;

    public MorrisGame(Player p1, Player p2) {
        subscribers = new ArrayList<>();
        players = new Player[] {p1, p2};
        currentPlayerTurn = p1;
        gameBoard = new MorrisBoard();
    }

    public void addSubscriber(IMorrisGameSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void udpateSubscribers() {
        for (IMorrisGameSubscriber subscriber : subscribers) {
            subscriber.update(currentPlayerTurn.getIsRed(), gameBoard.generatePlayerBoard(currentPlayerTurn.getIsRed()));
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

    protected void setCurrentPlayerTurn(Player currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }
}
