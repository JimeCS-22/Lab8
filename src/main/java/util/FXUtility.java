package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Optional;

public class FXUtility {

    public static Alert alert(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        // Eliminamos la línea alert.show() para que no se muestre automáticamente
        return alert;
    }

    public static TextInputDialog dialog(String title, String header) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        return dialog;
    }

    public static void loadPage(String className, String page, Pane pane) {
        try {
            Class cl = Class.forName(className);
            FXMLLoader fxmlLoader = new FXMLLoader(cl.getResource(page));

            Pane newPage = fxmlLoader.load();

            //Limpiar el pane actual y agregar la nueva página
            pane.getChildren().clear();  // Limpiar cualquier contenido previo
            pane.getChildren().add(newPage);  // Agregar la nueva escena

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void showErrorAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);

        // Mostrar la alerta
        alert.showAndWait();
    }

    public static void showMessage(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static Optional<ButtonType> showAlert(String title, String message, Alert.AlertType information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // Sin encabezado
        alert.setContentText(message);
        alert.showAndWait();
        return null;
    }

    public static Optional<ButtonType> showConfirmation(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        ButtonType yesButton = new ButtonType("Sí");
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, noButton);

        return alert.showAndWait(); // Devuelve la opción seleccionada
    }

}//END CLASS