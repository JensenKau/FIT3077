package ninemanmorris.gamelogic;

import ninemanmorris.gamelogic.game.LocalGame;
import ninemanmorris.gamelogic.game.MorrisGame;
import ninemanmorris.player.PlayerFactory;
import ninemanmorris.player.PlayerType;

public class MorrisGameFactory {
    
    private MorrisGameFactory() {

    }

    public static MorrisGame createMorrisGame(PlayerType p1, PlayerType p2, IMorrisGameSubscriber subscriber) {
        return new LocalGame(PlayerFactory.createPlayer(p1, true), PlayerFactory.createPlayer(p2, false), subscriber);
    }
}
