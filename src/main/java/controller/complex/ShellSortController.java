package controller.complex;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ShellSortController {

    @FXML
    private TextField gapTextField;
    @FXML
    private TextField gap2TextField;
    @FXML
    private TextField lengthTextField;
    @FXML
    private TextField lowTextField1;
    @FXML
    private TextField highTextField11;
    @FXML
    private TableView<ObservableList<Integer>> noSortedTV;
    @FXML
    private TableView<ObservableList<Integer>> sortedTV;
    @FXML
    private TextField gap1TextField;
    @FXML
    private TextField gap3TextField;

    private int[] originalArray;
    private int[] sortedArray;
    private int[] gaps; // Changed to an array
    @FXML
    private Pane mainPain;
    @FXML
    private Pane buttonPane;
    @FXML
    private AnchorPane AP;

    private void configureTableView(TableView<ObservableList<Integer>> tableView, String columnName) {
        TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(columnName);
        column.setCellValueFactory(cellData ->
                cellData.getValue().isEmpty() ? new SimpleIntegerProperty(0).asObject() :
                        new SimpleIntegerProperty(cellData.getValue().get(0)).asObject());
        tableView.getColumns().add(column);
        tableView.setItems(FXCollections.observableArrayList());
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {

        lowTextField1.clear();
        highTextField11.clear();
        gapTextField.clear();
        gap1TextField.clear();
        gap2TextField.clear();
        gap3TextField.clear();
        clearAllTables();
        originalArray = null;
        sortedArray = null;
        gaps = null; // Changed to null
    }

    @FXML
    public void createOnAction(ActionEvent actionEvent) {
        try {
            int length = Integer.parseInt(lengthTextField.getText());
            int lowerBound = Integer.parseInt(lowTextField1.getText());
            int upperBound = Integer.parseInt(highTextField11.getText());

            if (length <= 0 || length > 200 || lowerBound > upperBound) {
                FXUtility.showAlert("Input error",
                        "Please enter a valid length (1-200) and consistent boundaries", null);
                clearAllTables();
                return;
            }

            originalArray = new int[length];
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                originalArray[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            }
            displayArray(noSortedTV, originalArray);
            FXUtility.showAlert("Array created", "An original array of length " + length + " has been created", null);
            clearSortedTable();

        } catch (NumberFormatException e) {
            FXUtility.showAlert(
                    "Format error", "Please enter valid numbers for length and boundaries", null);
            clearAllTables();
        }
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {
        if (originalArray != null && originalArray.length > 0) {
            try {
                int lowerBound = Integer.parseInt(lowTextField1.getText());
                int upperBound = Integer.parseInt(highTextField11.getText());
                Random random = new Random();
                for (int i = 0; i < originalArray.length; i++) {
                    originalArray[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                }
                displayArray(noSortedTV, originalArray);
                FXUtility.showAlert("Array Randomized", "The original array has been randomized", null);
                clearSortedTable();
                gapTextField.clear();
                gap1TextField.clear();
                gap2TextField.clear();
                gap3TextField.clear();
            } catch (NumberFormatException e) {
                FXUtility.showAlert("Format error", "Please enter valid numbers for the boundaries", null);
            }
        } else {
            FXUtility.showAlert("Warning", "Please create an array first before randomizing", null);
        }
    }

    @FXML
    public void startOnAction(ActionEvent actionEvent) {
        if (originalArray != null && originalArray.length > 0) {
            sortedArray = Arrays.copyOf(originalArray, originalArray.length);
            int n = sortedArray.length;
            int gapCount = 0;
            int gap = n / 2;
            int[] calculatedGaps = new int[4]; // To store the first few gaps for display

            displayArray(noSortedTV, originalArray); // Show the original array

            while (gap > 0) {
                if (gapCount < calculatedGaps.length) {
                    calculatedGaps[gapCount++] = gap;
                }
                sortWithGap(sortedArray, gap);
                gap /= 2;
            }
            gaps = calculatedGaps; // Store the first few calculated gaps (or fewer if the array is small)

            displayGapValues();
            displayArray(sortedTV, sortedArray); // Display the sorted array
            FXUtility.showAlert("Shell Sort Completed", "The Shell Sort algorithm has been executed", null);

        } else {
            FXUtility.showAlert("Warning", "Please create an array first before executing Shell Sort", null);
        }
    }

    private void sortWithGap(int[] arr, int gap) {
        int n = arr.length;
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j = i;
            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
    }

    private void displayGapValues() {
        if (gaps != null) {
            if (gaps.length > 0 && gapTextField != null) {
                gapTextField.setText(Arrays.toString(Arrays.copyOf(sortedArray, sortedArray.length)));
            }
            if (gaps.length > 1 && gap1TextField != null) {
                gap1TextField.setText(Arrays.toString(Arrays.copyOf(sortedArray, sortedArray.length)));
            }
            if (gaps.length > 2 && gap2TextField != null) {
                gap2TextField.setText(Arrays.toString(Arrays.copyOf(sortedArray, sortedArray.length)));
            }
            if (gaps.length > 3 && gap3TextField != null) {
                gap3TextField.setText(Arrays.toString(Arrays.copyOf(sortedArray, sortedArray.length)));
            }
        } else {
            if (gapTextField != null) gapTextField.clear();
            if (gap1TextField != null) gap1TextField.clear();
            if (gap2TextField != null) gap2TextField.clear();
            if (gap3TextField != null) gap3TextField.clear();
        }
    }

    private void displayArray(TableView<ObservableList<Integer>> tableView, int[] array) {
        tableView.getColumns().clear();
        if (array != null) {
            ObservableList<Integer> row = FXCollections.observableArrayList();
            for (int value : array) {
                row.add(value);
            }
            // Create columns based on the size of the array (number of elements)
            for (int i = 0; i < array.length; i++) {
                final int columnIndex = i;
                TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(String.valueOf(i)); // Use index as column header
                column.setCellValueFactory(cellData -> {
                    if (columnIndex < cellData.getValue().size()) {
                        return new SimpleIntegerProperty(cellData.getValue().get(columnIndex)).asObject();
                    } else {
                        return new SimpleIntegerProperty(0).asObject(); // Handle potential out-of-bounds
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
    }

    private void clearSortedTable() {
        sortedTV.getItems().clear();
        sortedTV.getColumns().clear();
    }
}