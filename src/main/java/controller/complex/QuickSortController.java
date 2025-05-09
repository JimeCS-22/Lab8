package controller.complex;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static domain.complex.quickSort;
import static domain.complex.quickSortRecursiveCalls;

public class QuickSortController {

    @FXML
    private AnchorPane AP;

    @FXML
    private Pane mainPain;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField nameTextField1;

    @FXML
    private TextField nameTextField11;

    @FXML
    private TextField nameTextField12;

    @FXML
    private TextField nameTextField121;

    @FXML
    private TextField nameTextField122;

    @FXML
    private TextField nameTextField1221;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableView<?> tableView1;

    @FXML
    private Text txtMessage;

    private int[] originalArray;
    private int[] sortedArray;
    public static List<int[]> pivotValues = new ArrayList<>();


    @FXML
    public void createOnAction(ActionEvent actionEvent) {

        try {
            int length = Integer.parseInt(nameTextField.getText());
            int lowerBound = Integer.parseInt(nameTextField1.getText());
            int upperBound = Integer.parseInt(nameTextField11.getText());

            if (length <= 0 || length > 200 || lowerBound > upperBound) {
                util.FXUtility.showErrorAlert("Input Error", "Please enter a valid length (1–200) and consistent bounds.");
                clearAllTables();
                return;
            }

            originalArray = new int[length];
            for (int i = 0; i < length; i++) {
                originalArray[i] = util.Utility.randomInRange(lowerBound , upperBound);
            }
            displayArray((TableView<ObservableList<Integer>>) tableView, originalArray, "Original Array");
            util.FXUtility.showMessage("Array Created", "An original array of length " + length + " has been created.");
            tableView1.getItems().clear();
            tableView1.getColumns().clear();

        } catch (NumberFormatException e) {
            util.FXUtility.showErrorAlert("Format Error", "Please enter valid numbers for the length and bounds.");
            clearAllTables();
        }

    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {

        Optional<ButtonType> result = util.FXUtility.showConfirmation(
                "Confirmation",
                "Are you sure you want to delete the data?",
                "This action will delete the original array, the sorted array, and all auxiliary data."
        );
        if (result.isPresent() && result.get().getText().equals("Sí")) {
            clearAllTables();
            clearAuxiliaryTextFields();
            originalArray = null;
            sortedArray = null;
            pivotValues.clear();
            nameTextField.clear();
            nameTextField1.clear();
            nameTextField11.clear();
            nameTextField12.clear();
            nameTextField121.clear();
            nameTextField122.clear();
            nameTextField1221.clear();
            txtMessage.setText("Data deleted.");
        }
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {

        if (originalArray != null && originalArray.length > 0) {
            int lowerBound = Integer.parseInt(nameTextField1.getText());
            int upperBound = Integer.parseInt(nameTextField11.getText());
            for (int i = 0; i < originalArray.length; i++) {
                originalArray[i] = util.Utility.randomInRange(lowerBound , upperBound);
            }
            displayArray((TableView<ObservableList<Integer>>) tableView, originalArray, "Original Array");
            tableView1.getItems().clear();
            tableView1.getColumns().clear();
            util.FXUtility.showMessage("Array Randomized" , "The original array has been randomized." );
        } else {
            util.FXUtility.showAlert("Warning", "Please create an array first.", null);
        }
    }

    @FXML
    public void starOnAction(ActionEvent actionEvent) {
        if (originalArray != null && originalArray.length > 0) {
            sortedArray = util.Utility.copyArray(originalArray);
            quickSortRecursiveCalls = 0;
            pivotValues.clear();
            quickSortWithTracking(sortedArray, 0, sortedArray.length - 1); // Aquí el cambio
            displayArray((TableView<ObservableList<Integer>>) tableView1, sortedArray, "Sorted Array");
            nameTextField1221.setText(String.valueOf(quickSortRecursiveCalls));
            displayPivotValues();
            util.FXUtility.showMessage("Quick Sort Completed", "The array has been sorted");
        } else {
            util.FXUtility.showAlert("Warning", "Please create an array first.", null);
        }
    }

    private void displayArray(TableView<ObservableList<Integer>> tableView, int[] array, String tableName) {
        tableView.getColumns().clear();
        if (array != null) {
            ObservableList<Integer> row = FXCollections.observableArrayList();
            for (int i = 0; i < array.length; i++) {
                row.add(array[i]);
                TableColumn<ObservableList<Integer>, Integer> column = new TableColumn<>(String.valueOf(i)); // Índice como título
                final int columnIndex = i;
                column.setCellValueFactory(cellData -> {
                    if (columnIndex < cellData.getValue().size()) {
                        return new SimpleIntegerProperty(cellData.getValue().get(columnIndex)).asObject();
                    } else {
                        return new SimpleIntegerProperty(0).asObject(); // Evitar IndexOutOfBounds
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
        tableView.getItems().clear();
        tableView.getColumns().clear();
        tableView1.getItems().clear();
        tableView1.getColumns().clear();
    }

    private void displayPivotValues() {
        if (!pivotValues.isEmpty()) {
            List<Integer> lowValues = new ArrayList<>();
            List<Integer> highValues = new ArrayList<>();
            List<Integer> pivotVals = new ArrayList<>();

            for (int[] values : pivotValues) {
                if (values.length == 3) {
                    lowValues.add(values[0]);
                    highValues.add(values[1]);
                    pivotVals.add(values[2]);
                }
            }
            nameTextField12.setText(lowValues.toString());
            nameTextField121.setText(highValues.toString());
            nameTextField122.setText(pivotVals.toString());
        } else {
            nameTextField12.setText("");
            nameTextField121.setText("");
            nameTextField122.setText("");
        }
    }

    private void clearAuxiliaryTextFields() {
        nameTextField12.clear();
        nameTextField121.clear();
        nameTextField122.clear();
        nameTextField1221.clear();
    }

    private void quickSortWithTracking(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = arr[(low + high) / 2];
            QuickSortController.pivotValues.add(new int[]{low, high, pivot});

            // Llama a la función original (que no puedes modificar)
            domain.complex.quickSort(arr, low, high);
        }
    }


}

