package ninemanmorris.gamelogic;

import ninemanmorris.gamelogic.game.LocalGame;
import ninemanmorris.gamelogic.game.MorrisGame;
import ninemanmorris.player.PlayerFactory;
import ninemanmorris.player.PlayerType;

/**
 * A factory class for creating MorrisGame
 */
public class MorrisGameFactory {
    
    /**
     * Private constructor to prevent instantiation
     */
    private MorrisGameFactory() {

    }

    /**
     * Creates a MorrisGame based on the specified parameter
     * @param p1 - the player type of player 1 (red player)
     * @param p2 - the player type of player 2 (blue player)
     * @param subscriber - the subscriber that wants to subscribe to the morris game
     * @return The newly created MorrisGame based on the specified parameters
     */
    public static MorrisGame createMorrisGame(PlayerType p1, PlayerType p2, IMorrisGameSubscriber subscriber) {
        return new LocalGame(PlayerFactory.createPlayer(p1, true), PlayerFactory.createPlayer(p2, false), subscriber);
    }
}
