package controller.elementary;

import domain.elementary;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.FXUtility;

import java.util.Arrays;
import java.util.Random;

public class ImprovedBubbleSortController {

    @FXML private TextField totalIterationsTextField;
    @FXML private TextField totalChangesTextField;
    @FXML private TextField arrayLengthTextField;
    @FXML private TableView<int[]> noSortedArrayTableView;
    @FXML private TableView<int[]> sortedArrayTableView;
    @FXML private TextField lowTextField;
    @FXML private TextField highTextField;
    @FXML private Button createButton;
    @FXML private Button randomizeButton;
    @FXML private Button starButton;
    @FXML private Button cleanButton;

    private int[] numberArray;
    private final Random random = new Random();

    @FXML
    public void initialize() {
        totalIterationsTextField.setEditable(false);
        totalChangesTextField.setEditable(false);
        arrayLengthTextField.setTextFormatter(createNumberTextFormatter());
        lowTextField.setTextFormatter(createNumberTextFormatter());
        highTextField.setTextFormatter(createNumberTextFormatter());

        starButton.setDisable(true);
        randomizeButton.setDisable(true);
    }

    @FXML
    private void createOnAction(ActionEvent event) {
        try {
            // Validar campos
            if (arrayLengthTextField.getText().isEmpty() ||
                    lowTextField.getText().isEmpty() ||
                    highTextField.getText().isEmpty()) {
                FXUtility.showErrorAlert("Error", "Todos los campos son requeridos");
                return;
            }

            int length = Integer.parseInt(arrayLengthTextField.getText());
            int low = Integer.parseInt(lowTextField.getText());
            int high = Integer.parseInt(highTextField.getText());

            if (length <= 0 || length > 200) {
                FXUtility.showErrorAlert("Error", "La longitud debe estar entre 1 y 200");
                return;
            }

            if (low >= high) {
                FXUtility.showErrorAlert("Error", "El valor mínimo debe ser menor al máximo");
                return;
            }

            // Reset antes de crear nuevo array
            resetTables();

            // Crear y generar valores
            numberArray = new int[length];
            generateRandomValues(low, high);

            // Configurar y mostrar datos
            safeTableUpdate(noSortedArrayTableView, numberArray);

            // Habilitar botones
            randomizeButton.setDisable(false);
            starButton.setDisable(false);

            FXUtility.showMessage("Éxito", "Array creado y valores generados");

        } catch (NumberFormatException e) {
            FXUtility.showErrorAlert("Error", "Ingrese valores numéricos válidos");
        }
    }

    @FXML
    private void randomizeOnAction(ActionEvent event) {
        try {
            int low = Integer.parseInt(lowTextField.getText());
            int high = Integer.parseInt(highTextField.getText());

            // Regenerar valores
            generateRandomValues(low, high);

            // Actualizar la tabla no ordenada
            safeTableUpdate(noSortedArrayTableView, numberArray);

            FXUtility.showMessage("Éxito", "Nuevos valores aleatorios generados");

        } catch (NumberFormatException e) {
            FXUtility.showErrorAlert("Error", "Error al regenerar valores");
        }
    }

    @FXML
    private void starOnAction(ActionEvent event) {
        if (numberArray == null) {
            FXUtility.showErrorAlert("Error", "Primero debe crear el array");
            return;
        }

        // Ordenar
        int[] sortedArray = numberArray.clone();
        elementary.improvedBubbleSort(sortedArray);

        // Configurar tabla ordenada si es la primera vez
        if (sortedArrayTableView.getColumns().isEmpty()) {
            configureTableColumns(sortedArrayTableView, sortedArray.length);
        }

        // Mostrar resultados
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getItems().add(sortedArray);

        totalIterationsTextField.setText(String.valueOf(elementary.getTotalIteractions()));
        totalChangesTextField.setText(String.valueOf(elementary.getTotalChanges()));

        FXUtility.showMessage("Éxito", "Array ordenado correctamente");
    }

    @FXML
    private void cleanOnAction(ActionEvent event) {
        resetTables();
        arrayLengthTextField.clear();
        lowTextField.clear();
        highTextField.clear();
        totalChangesTextField.clear();
        totalIterationsTextField.clear();
        numberArray = null;
        starButton.setDisable(true);
        randomizeButton.setDisable(true);
    }

    // ===== Métodos auxiliares =====
    private void generateRandomValues(int low, int high) {
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = random.nextInt(high - low + 1) + low;
        }
    }

    private void configureTableColumns(TableView<int[]> tableView, int length) {
        tableView.getColumns().clear();

        for (int i = 0; i < length; i++) {
            final int columnIndex = i;
            TableColumn<int[], String> column = new TableColumn<>(String.valueOf(i));
            column.setCellValueFactory(data -> {
                int[] row = data.getValue();
                return new SimpleStringProperty(
                        (row != null && columnIndex < row.length) ?
                                String.valueOf(row[columnIndex]) : ""
                );
            });
            column.setPrefWidth(50);
            tableView.getColumns().add(column);
        }
    }

    private void safeTableUpdate(TableView<int[]> tableView, int[] data) {
        tableView.getItems().clear();
        configureTableColumns(tableView, data.length);
        tableView.getItems().add(data.clone());
    }

    private void resetTables() {
        noSortedArrayTableView.getItems().clear();
        noSortedArrayTableView.getColumns().clear();
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getColumns().clear();
    }

    private TextFormatter<String> createNumberTextFormatter() {
        return new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("-?\\d*")) {
                return change;
            }
            return null;
        });
    }
}

