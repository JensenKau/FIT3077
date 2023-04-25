package ninemanmorris.gamelogic;

public class Token {
    
    private boolean isRedPlayer;

    public Token(boolean isRedPlayer) {
        this.isRedPlayer = isRedPlayer;
    }

    public boolean getIsRedPlayer() {
        return this.isRedPlayer;
    }
}
