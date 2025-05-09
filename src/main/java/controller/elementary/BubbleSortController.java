package controller.elementary;

import domain.elementary;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.FXUtility;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class BubbleSortController {

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
            // Validate fields
            if (arrayLengthTextField.getText().isEmpty() ||
                    lowTextField.getText().isEmpty() ||
                    highTextField.getText().isEmpty()) {
                FXUtility.showErrorAlert("Error", "All fields are required");
                return;
            }

            int length = Integer.parseInt(arrayLengthTextField.getText());
            int low = Integer.parseInt(lowTextField.getText());
            int high = Integer.parseInt(highTextField.getText());

            if (length <= 0 || length > 200) {
                FXUtility.showErrorAlert("Error", "Length must be between 1 and 200");
                return;
            }

            if (low >= high) {
                FXUtility.showErrorAlert("Error", "Minimum value must be less than maximum");
                return;
            }

            // Reset before creating new array
            resetTables();

            // Create and generate values
            numberArray = new int[length];
            generateRandomValues(low, high);

            // Configure and show data
            safeTableUpdate(noSortedArrayTableView, numberArray);

            // Enable buttons
            randomizeButton.setDisable(false);
            starButton.setDisable(false);

            FXUtility.showMessage("Success", "Array created and values generated");

        } catch (NumberFormatException e) {
            FXUtility.showErrorAlert("Error", "Please enter valid numeric values");
        }
    }

    @FXML
    private void randomizeOnAction(ActionEvent event) {
        try {
            int low = Integer.parseInt(lowTextField.getText());
            int high = Integer.parseInt(highTextField.getText());

            // Regenerate values
            generateRandomValues(low, high);

            // Update unsorted table
            safeTableUpdate(noSortedArrayTableView, numberArray);

            FXUtility.showMessage("Success", "New random values generated");

        } catch (NumberFormatException e) {
            FXUtility.showErrorAlert("Error", "Error while generating new values");
        }
    }

    @FXML
    private void starOnAction(ActionEvent event) {
        if (numberArray == null) {
            FXUtility.showErrorAlert("Error", "You must create an array first");
            return;
        }

        // Sort
        int[] sortedArray = numberArray.clone();
        elementary.bubbleSort(sortedArray);

        // Configure sorted table if first time
        if (sortedArrayTableView.getColumns().isEmpty()) {
            configureTableColumns(sortedArrayTableView, sortedArray.length);
        }

        // Show results
        sortedArrayTableView.getItems().clear();
        sortedArrayTableView.getItems().add(sortedArray);

        totalIterationsTextField.setText(String.valueOf(elementary.getTotalIteractions()));
        totalChangesTextField.setText(String.valueOf(elementary.getTotalChanges()));

        FXUtility.showMessage("Success", "Array sorted successfully");
    }

    @FXML
    private void cleanOnAction(ActionEvent event) {
        Optional<ButtonType> result = FXUtility.showConfirmation("Clear All",
                "Confirm Clear Operation",
                "This will reset all fields and clear the tables. Continue?");

        if (result.isPresent() && result.get().getText().equals("Sí")) {
            try {
                resetTables();
                arrayLengthTextField.clear();
                lowTextField.clear();
                highTextField.clear();
                totalChangesTextField.clear();
                totalIterationsTextField.clear();
                numberArray = null;
                starButton.setDisable(true);
                randomizeButton.setDisable(true);

                FXUtility.showMessage("Cleared", "All fields have been reset successfully");
            } catch (Exception e) {
                FXUtility.showErrorAlert("Error", "Failed to clear fields: " + e.getMessage());
            }
        } else {
            FXUtility.showMessage("Cancelled", "Operation was cancelled by user");
        }
    }

    // ===== Helper methods =====
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