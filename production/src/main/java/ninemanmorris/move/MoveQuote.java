package ninemanmorris.move;

/**
 * Enum which represents the different phases in the game mapped to their quotes
 */
public enum MoveQuote {
    PLACE_PHASE("Place your tokens on the board"),
    MOVE_PHASE("Move your tokens to any empty adjacent spot"),
    FLY_PHASE("Move your tokens to any empty spot"),
    REMOVE_PHASE("Remove one of your opponent's token"),
;

    private final String TEXT;

    /**
     * Initialise a MoveQuote enum with its corresponding quote
     * @param TEXT
     */
    MoveQuote(final String TEXT) {
        this.TEXT = TEXT;
    }

    /**
     * Returns the quote
     */
    @Override
    public String toString() {
        return this.TEXT;
    }
}