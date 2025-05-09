package controller.elementary;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javafx.collections.ObservableList;
import util.FXUtility;

import java.util.Collections;
import java.util.Optional;

import static domain.elementary.countingSort;

public class CountingSortController {

    public Button cleanButton;
    public Button autoButton;
    public Button attentionButton;
    public Button autoButton1;
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
    private TableView<ObservableList<Integer>> tableView;

    @FXML
    private TableView<ObservableList<Integer>> tableView1;

    @FXML
    private TableView<ObservableList<Integer>> tableView11;

    @FXML
    private Text txtMessage;

    private int[] originalArray;
    private int[] sortedArray;


    @FXML
    public void createOnAction(ActionEvent actionEvent) {

        try {
            int length = Integer.parseInt(nameTextField.getText());
            int lowerBound = Integer.parseInt(nameTextField1.getText());
            int upperBound = Integer.parseInt(nameTextField11.getText());

            if (length <= 0 || length > 200 || lowerBound > upperBound) {
                util.FXUtility.showAlert("Input error" ,
                        "Please enter a valid length (1-200) and consistent boundaries.", null);
                clearAllTables();
                return;
            }

            originalArray = new int[length];
            for (int i = 0; i < length; i++) {
                originalArray[i] = util.Utility.random(length);
            }
            displayArray(tableView, originalArray, "Arreglo Original");
            util.FXUtility.showAlert("Array created" , "An original length arrangement has been created" + length + ".", null);
            clearCountingAndSortedTables();

        } catch (NumberFormatException e) {
            util.FXUtility.showAlert(
                    "Format error","Please enter valid numbers for length and boundaries." , null);
            clearAllTables();

        }

    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {

        Optional<ButtonType> result = FXUtility.showConfirmation("Confirmation",
                "Are you sure you want to clear the fields and tables?",
                "This action will delete all current information.");

        if (result.isPresent() && result.get().getText().equals("Sí")) {
            clearAllTables();
            nameTextField.clear();
            nameTextField1.clear();
            nameTextField11.clear();
            originalArray = null;
            sortedArray = null;
        }
    }

    @FXML
    public void randomizeOnAction(ActionEvent actionEvent) {

        if (originalArray != null && originalArray.length > 0) {
            int lowerBound = Integer.parseInt(nameTextField1.getText());
            int upperBound = Integer.parseInt(nameTextField11.getText());
            for (int i = 0; i < originalArray.length; i++) {
                originalArray[i] = util.Utility.randomInRange(lowerBound, upperBound);
            }
            displayArray(tableView, originalArray, "Original Array");
            util.FXUtility.showAlert("Array Randomized", "The original array has been randomized.", null);
            clearCountingAndSortedTables();

        } else {
            util.FXUtility.showAlert("Warning", "Please create an array first before randomizing.", null);
        }

    }

    @FXML
    public void starOnAction(ActionEvent actionEvent) {

        if (originalArray != null && originalArray.length > 0) {
            sortedArray = util.Utility.copyArray(originalArray);
            countingSort(sortedArray);

            displayArray(tableView, originalArray, "Original Array");
            displayArray(tableView11, sortedArray, "Sorted Array");
            int[] counter = calcularArregloConteo(originalArray);
            displayArray(tableView1, counter, "Counting Array");
            util.FXUtility.showAlert("Counting Sort Completed", "The Counting Sort algorithm has been successfully executed.", null);
        } else {
            util.FXUtility.showAlert("Warning", "Please create an array first before executing Counting Sort.", null);
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
        tableView11.getItems().clear();
        tableView11.getColumns().clear();
    }

    private void clearCountingAndSortedTables() {
        tableView1.getItems().clear();
        tableView1.getColumns().clear();
        tableView11.getItems().clear();
        tableView11.getColumns().clear();
    }

    private int[] calcularArregloConteo(int[] array) {
        if (array == null || array.length == 0) return new int[0];

        int max = array[0];
        for (int val : array) {
            if (val > max) max = val;
        }

        int[] counter = new int[max + 1];
        for (int val : array) {
            counter[val]++;
        }

        return counter;
    }

}