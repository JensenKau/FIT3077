package ninemanmorris.screen;

public enum ScreenPage {
    TITLE_SCREEN("fxml/title_screen.fxml"),
    GAME_SCREEN("fxml/game_screen.fxml"),
    INSTRUCTION_SCREEN(""),
    RESULT_SCREEN("");

    private final String TEXT;

    ScreenPage(final String TEXT) {
        this.TEXT = TEXT;
    }

    @Override
    public String toString() {
        return this.TEXT;
    }
}