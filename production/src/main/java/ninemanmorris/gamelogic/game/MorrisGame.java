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
     * @param p1 - player 1 (red player)
     * @param p2 - player 2 (blue player)
     * @param subscriber - the subscriber who will be observing this 
     * class
     */
    public MorrisGame(Player p1, Player p2, IMorrisGameSubscriber subscriber) {
        subscribers = new ArrayList<>();
        players = new Player[] {p1, p2};
        currentPlayerTurn = p1;
        gameBoard = new MorrisBoard();

        addSubscriber(subscriber);
    }

    /**
     * Add a subscriber to this class and update the subscriber with 
     * the state 
     * of the board
     * @param subscriber - the new subscriber
     */
    public void addSubscriber(IMorrisGameSubscriber subscriber) {
        subscribers.add(subscriber);
        subscriber.update(
            currentPlayerTurn.getIsRed(), 
            players[0].getTokenCount(), 
            players[1].getTokenCount(), 
            gameBoard.generatePlayerBoard(), 
            gameBoard.generatePreviewMove(currentPlayerTurn.getMove()), 
            gameBoard.generateMills(), 
            currentPlayerTurn.getMoveType(),
            gameBoard.getSelectedPos(currentPlayerTurn.getMove())
        );
    }

    /**
     * Update all of the subscribers subscribed to this class with the 
     * new state of the board
     */ 
    public void udpateSubscribers() {
        for (IMorrisGameSubscriber subscriber : subscribers) {
            subscriber.update(
                currentPlayerTurn.getIsRed(), 
                players[0].getTokenCount(), 
                players[1].getTokenCount(), 
                gameBoard.generatePlayerBoard(), 
                gameBoard.generatePreviewMove(currentPlayerTurn.getMove()), 
                gameBoard.generateMills(), 
                currentPlayerTurn.getMoveType(),
                gameBoard.getSelectedPos(currentPlayerTurn.getMove())
            );
        }
    }

    /**
     * Declare whether the winner of the game is the red token 
     * (player 1) or blue token (player 2) for all subscribers 
     * subscribed to this class
     * @param isRed - true if the winner is the red token (player 1)
     * and false if it is not
     */
    protected void delcareWinner(boolean isRed) {
        for (IMorrisGameSubscriber subscriber : subscribers) {
            subscriber.updateGameEnd(isRed);
        }
    }

    /**
     * Declare that the game has come to a draw for all subscribers 
     * subscribed to this class
     */
    protected void declareDraw() {
        for (IMorrisGameSubscriber subscriber : subscribers) {
            subscriber.updateGameDraw();
        }
    }

    /**
     * Validate and set the moves for both players
     */
    protected void validatePlayerMove() {
        players[0].setMove(gameBoard.validatePlayerMove(players[0].getMove()));
        players[1].setMove(gameBoard.validatePlayerMove(players[1].getMove()));
    }

    /**
     * Get the morris game board
     * @return the morris game board
     */
    protected MorrisBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Get the players that is playing this game
     * @return the two players playing this game
     */
    protected Player[] getPlayers() {
        return players;
    }

    /**
     * Get the current player turn
     * @return the current player who is playing the turn currently
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
