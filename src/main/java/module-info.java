module ucr.lab8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ucr.lab8 to javafx.fxml;
    exports ucr.lab8;
    exports controller;
    opens controller to javafx.fxml;
    exports controller.elementary;
    opens controller.elementary to javafx.fxml;
}