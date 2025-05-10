package controller.complex;

import domain.complex;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.FXUtility;
import util.Utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MergeSortController {

      @FXML
    private TextField recursiveCallTextField;
    @FXML
    private TableView<ObservableList<Integer>> tempTV;
    @FXML
    private TextField lengthTextField;
    @FXML
    private TextField lowTextField2; // To display the "Low" part of the sorted array
    @FXML
    private TextField lowTextField1; // For user input of lower bound
    @FXML
    private TextField highTextField11; // For user input of upper bound
    @FXML
    private TableView<ObservableList<Integer>> noSortedTV;
    @FXML
    private TableView<ObservableList<Integer>> sortedTV;
    @FXML
    private TextField highTextField2; // To display the "High" part of the sorted array

    private int[] originalArray;
    private int[] sortedArray;
    private int[] tempArray;
    private static final int LIMIT_LOW_HIGH = 10; // Consistent limit for Low and High display

    public void initialize() {

    }

    private void configureTableView(TableView<ObservableList<Integer>> tableView, String columnName) {
        TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(columnName);
        column.setCellValueFactory(cellData ->
                cellData.getValue().isEmpty() ? new SimpleIntegerProperty(0).asObject() :
                        new SimpleIntegerProperty(cellData.getValue().get(0)).asObject());
        tableView.getColumns().add(column);
        tableView.setItems(FXCollections.observableArrayList());
    }

    @FXML
    void clearOnAction(ActionEvent event) {
        lengthTextField.clear();
        lowTextField1.clear();
        highTextField11.clear();
        recursiveCallTextField.clear();
        lowTextField2.clear();
        highTextField2.clear();
        clearAllTables();
        originalArray = null;
        sortedArray = null;
        tempArray = null;
    }

    @FXML
    public void createOnAction(ActionEvent actionEvent) {
        try {
            int length = Integer.parseInt(lengthTextField.getText());
            int lowerBound = Integer.parseInt(lowTextField1.getText());
            int upperBound = Integer.parseInt(highTextField11.getText());

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
            clearSortedAndTempTables();
        } catch (NumberFormatException e) {
            FXUtility.showAlert(
                    "Format error", "Please enter valid numbers for length and boundaries.", null);
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
                displayArray(noSortedTV, originalArray, "Unsorted Array");
                FXUtility.showAlert("Array Randomized", "The original array has been randomized.", null);
                clearSortedAndTempTables();
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
            sortedArray = Utility.copyArray(originalArray);
            tempArray = new int[sortedArray.length];
            complex.resetMergeSortCounters();
            complex.mergeSort(sortedArray, tempArray, 0, sortedArray.length - 1);
            recursiveCallTextField.setText(String.valueOf(complex.mergeSortRecursiveCalls));

            displayArray(noSortedTV, originalArray, "Unsorted Array");
            displayArray(sortedTV, sortedArray, "Sorted Array");
            displayArray(tempTV, tempArray, "Temporary Array");
            displayLowHighInTextFields(sortedArray, LIMIT_LOW_HIGH);

            FXUtility.showAlert("Merge Sort Completed", "The Merge Sort algorithm has been successfully executed.", null);
        } else {
            FXUtility.showAlert("Warning", "Please create an array first before executing Merge Sort.", null);
        }
    }

    private void displayLowHighInTextFields(int[] array, int limit) {
        if (array != null) {
            int lowLimit = Math.min(array.length, limit);
            int highLimitStart = Math.max(0, array.length - limit);
            int highLimitEnd = array.length;

            int[] lowArray = Arrays.copyOfRange(array, 0, lowLimit);
            int[] highArray = Arrays.copyOfRange(array, highLimitStart, highLimitEnd);

            lowTextField2.setText(Utility.show(lowArray, lowArray.length));
            highTextField2.setText(Utility.show(highArray, highArray.length));
        } else {
            lowTextField2.clear();
            highTextField2.clear();
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
        tempTV.getItems().clear();
        tempTV.getColumns().clear();
    }

    private void clearSortedAndTempTables() {
        sortedTV.getItems().clear();
        sortedTV.getColumns().clear();
        tempTV.getItems().clear();
        tempTV.getColumns().clear();
        recursiveCallTextField.clear();
        lowTextField2.clear();
        highTextField2.clear();
    }
}