module ninemanmorris {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens ninemanmorris to javafx.fxml;
    opens ninemanmorris.Controller to javafx.fxml;

    exports ninemanmorris;
    exports ninemanmorris.Controller;
}
