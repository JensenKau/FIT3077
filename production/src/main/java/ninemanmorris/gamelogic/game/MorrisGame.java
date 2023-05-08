package ninemanmorris.gamelogic.game;

import java.util.ArrayList;

import ninemanmorris.gamelogic.IMorrisGameInputHandler;
import ninemanmorris.gamelogic.IMorrisGameSubscriber;
import ninemanmorris.gamelogic.MorrisBoard;
import ninemanmorris.player.playertype.Player;

/**
 * Represents a single game of the 9 men's morris
 */
public abstract class MorrisGame implements IMorrisGameInputHandler {
    
    private MorrisBoard gameBoard;
    private Player[] players;
    private Player currentPlayerTurn;
    private ArrayList<IMorrisGameSubscriber> subscribers;

    /**
     * Constructor to create a MorrisGame
     * @param p1 - Player 1 (red player)
     * @param p2 - Player 2 (blue player)
     * @param subscriber - The subscriber who will be observing this class
     */
    public MorrisGame(Player p1, Player p2, IMorrisGameSubscriber subscriber) {
        subscribers = new ArrayList<>();
        players = new Player[] {p1, p2};
        currentPlayerTurn = p1;
        gameBoard = new MorrisBoard();

        addSubscriber(subscriber);
    }

    /**
     * Add a subscriber to this class
     * @param subscriber - The new subscriber
     */
    public void addSubscriber(IMorrisGameSubscriber subscriber) {
        subscribers.add(subscriber);
        subscriber.update(currentPlayerTurn.getIsRed(), gameBoard.generatePlayerBoard(), currentPlayerTurn.getMove().getMoveQuote());
    }

    /**
     * Update the subscribers subscribed to this class
     */
    public void udpateSubscribers() {
        for (IMorrisGameSubscriber subscriber : subscribers) {
            subscriber.update(currentPlayerTurn.getIsRed(), gameBoard.generatePlayerBoard(), currentPlayerTurn.getMove().getMoveQuote());
        }
    }

    /**
     * Get the morris game board
     * @return The morris game board
     */
    protected MorrisBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Get the players that is playing this game
     * @return The two players playing this game
     */
    protected Player[] getPlayers() {
        return players;
    }

    /**
     * Get the current player turn
     * @return The current player who is playing the turn currently
     */
    protected Player getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    /**
     * Switch the player turn
     */
    protected void switchPlayerTurn() {
        if (currentPlayerTurn == players[0]) {
            this.currentPlayerTurn = players[1];
        } else {
            this.currentPlayerTurn = players[0];
        }
    }
}
