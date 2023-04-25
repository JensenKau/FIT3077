package ninemanmorris.gamelogic;

public interface IMorrisGameSubscriber {

    void update(boolean isRed, Boolean[][] board);

    void updateGameEnd(boolean isRed);
}
