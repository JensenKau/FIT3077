package ninemanmorris.player.playertype;

public class HumanPlayer extends Player {

    private static final boolean IS_REQUIRE_INPUT = true;

    public HumanPlayer(boolean isRed) {
        super(isRed);
    }

    @Override
    public boolean getIsRequireInput() {
        return IS_REQUIRE_INPUT;
    }
}
