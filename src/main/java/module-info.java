module ucr.lab8 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ucr.lab8 to javafx.fxml;
    opens controller to javafx.fxml;
    opens controller.elementary to javafx.fxml;
    opens controller.complex to javafx.fxml;

    exports ucr.lab8;
    exports controller;
    exports controller.elementary;
    exports controller.complex;
}