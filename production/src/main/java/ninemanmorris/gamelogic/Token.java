package ninemanmorris.gamelogic;

import ninemanmorris.player.playertype.Player;

public class Token {
    
    private Player player;

    public Token(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getIsRedPlayer() {
        return player.getIsRed();
    }
}
