package ninemanmorris.screen;

/**
 * Enum which represents the different screens in the game by mapping to the screen fxml file directories
 */
public enum ScreenPage {
    TITLE_SCREEN("/fxml/title_screen.fxml"),
    GAME_SCREEN("/fxml/game_screen.fxml"),
    INSTRUCTION_SCREEN("/fxml/instruction_screen.fxml"),
    RESULT_SCREEN("/fxml/result_screen.fxml"),
    SELECT_SCREEN("/fxml/select_screen.fxml");

    private final String TEXT;

    /**
     * Initialise a ScreenPage enum with its corresponding fxml file directory 
     * @param TEXT
     */
    ScreenPage(final String TEXT) {
        this.TEXT = TEXT;
    }

    /**
     * Returns the fxml file directory 
     */
    @Override
    public String toString() {
        return this.TEXT;
    }
}