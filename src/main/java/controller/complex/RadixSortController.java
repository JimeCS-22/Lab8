package controller.complex;

import domain.complex;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.FXUtility;
import util.Utility;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;

public class RadixSortController {

    public Button cleanButton;
    public Button autoButton;
    public Button attentionButton;
    public Button autoButton1;
    @FXML
    private AnchorPane AP;

    @FXML
    private Pane mainPain;

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField lowTextField;

    @FXML
    private TextField highTextField;

    @FXML
    private TableView<ObservableList<Integer>> noSortedTV;

    @FXML
    private TableView<ObservableList<Integer>> sortedTV;

    @FXML
    private TableView<ObservableList<Integer>> counterTV;

    @FXML
    private Text txtMessage;

    private int[] originalArray;
    private int[] sortedArray;
    private int[] counterArray;

    @FXML
    public void createOnAction(ActionEvent actionEvent) {
        try {
            int length = Integer.parseInt(lengthTextField.getText());
            int lowerBound = Integer.parseInt(lowTextField.getText());
            int upperBound = Integer.parseInt(highTextField.getText());

            if (length <= 0 || length > 200 || lowerBound > upperBound) {
                FXUtility.showAlert("Input error",
                        "Please enter a valid length (1-200) and consistent boundaries.", null);
                clearAllTables();
                return;
            }

            originalArray = new int[length];
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                originalArray[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            }
            displayArray(noSortedTV, originalArray, "Unsorted Array");
            FXUtility.showAlert("Array created", "An original array of length " + length + " has been created.", null);
            clearSortedAndCounterTables();

        } catch (NumberFormatException e) {
            FXUtility.showAlert(
                    "Format error", "Please enter valid numbers for length and boundaries.", null);
            clearAllTables();
        }
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> result = FXUtility.showConfirmation("Confirmation",
                "Are you sure you want to clear the fields and tables?",
                "This action will delete all current information.");

        if (result.isPresent() && result.get().getText().equals("Sí")) {
            clearAllTables();
            lengthTextField.clear();
            lowTextField.clear();
            highTextField.clear();
            originalArray = null;
            sortedArray = null;
            counterArray = null;
        }
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        if (originalArray != null && originalArray.length > 0) {
            try {
                int lowerBound = Integer.parseInt(lowTextField.getText());
                int upperBound = Integer.parseInt(highTextField.getText());
                Random random = new Random();
                for (int i = 0; i < originalArray.length; i++) {
                    originalArray[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                }
                displayArray(noSortedTV, originalArray, "Unsorted Array");
                FXUtility.showAlert("Array Randomized", "The original array has been randomized.", null);
                clearSortedAndCounterTables();
            } catch (NumberFormatException e) {
                FXUtility.showAlert("Format error", "Please enter valid numbers for the boundaries.", null);
            }
        } else {
            FXUtility.showAlert("Warning", "Please create an array first before randomizing.", null);
        }
    }

    @FXML
    public void startOnAction(ActionEvent actionEvent) {
        if (originalArray != null && originalArray.length > 0) {
            int[] arrayToSort = Utility.copyArray(originalArray);
            Object[] radixSortResult = complex.radixSort(arrayToSort, arrayToSort.length);

            if (radixSortResult != null && radixSortResult.length > 0) {
                counterArray = (int[]) radixSortResult[0]; // Counter array is at index 0
                sortedArray = Utility.copyArray(arrayToSort); // Radix Sort modifies the original array
            } else {
                sortedArray = new int[0];
                counterArray = new int[0];
                FXUtility.showAlert("Warning", "Radix Sort did not return expected results.", null);
                return;
            }

            displayArray(noSortedTV, originalArray, "Unsorted Array");
            displayArray(sortedTV, sortedArray, "Sorted Array");
            displayArray(counterTV, counterArray, "Counter Array");
            FXUtility.showAlert("Radix Sort Completed", "The Radix Sort algorithm has been successfully executed.", null);
        } else {
            FXUtility.showAlert("Warning", "Please create an array first before executing Radix Sort.", null);
        }
    }

    private void displayArray(TableView<ObservableList<Integer>> tableView, int[] array, String tableName) {
        tableView.getColumns().clear();
        if (array != null) {
            ObservableList<Integer> row = FXCollections.observableArrayList();
            for (int i = 0; i < array.length; i++) {
                row.add(array[i]);
                TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(String.valueOf(i)); // Index as title
                final int columnIndex = i;
                column.setCellValueFactory(cellData -> {
                    if (columnIndex < cellData.getValue().size()) {
                        return new SimpleIntegerProperty(cellData.getValue().get(columnIndex)).asObject();
                    } else {
                        return new SimpleIntegerProperty(0).asObject(); // Avoid IndexOutOfBounds
                    }
                });
                tableView.getColumns().add(column);
            }
            tableView.setItems(FXCollections.observableArrayList(Collections.singleton(row)));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }

    private void clearAllTables() {
        noSortedTV.getItems().clear();
        noSortedTV.getColumns().clear();
        sortedTV.getItems().clear();
        sortedTV.getColumns().clear();
        counterTV.getItems().clear();
        counterTV.getColumns().clear();
    }

    private void clearSortedAndCounterTables() {
        sortedTV.getItems().clear();
        sortedTV.getColumns().clear();
        counterTV.getItems().clear();
        counterTV.getColumns().clear();
    }
}