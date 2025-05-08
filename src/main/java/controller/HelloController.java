package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import ucr.lab8.HelloApplication;

import java.io.IOException;

public class HelloController {

    @FXML
    private Text txtMessage;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    private void load(String form) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(form));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void Home(ActionEvent actionEvent) {
        this.txtMessage.setText("Laboratory 8");
        this.bp.setCenter(ap);
    }

    @FXML
    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void exampleOnMousePressed(Event event) {
        this.txtMessage.setText("Loading Example. Please wait!!!");

    }

    @FXML
    public void BubbleSortOnAction(ActionEvent actionEvent) {
        load("BubbleSort.fxml");
    }

    @FXML
    public void mergeSortOnAction(ActionEvent actionEvent) {
        load("MergeSort.fxml");
    }

    @FXML
    public void shellSortOnAction(ActionEvent actionEvent) {
        load("ShellSort.fxml");
    }

    @FXML
    public void quickSortOnAction(ActionEvent actionEvent) {
        load("QuickSorting.fxml");
    }

    @FXML
    public void countingSortOnAction(ActionEvent actionEvent) {
        load("CountingSorting.fxml");
    }

    @FXML
    public void impBubbleSortOnAction(ActionEvent actionEvent) {
        load("improvedBubbleSort.fxml");
    }

    @FXML
    public void selectionSortOnAction(ActionEvent actionEvent) {
        load("SelectionSorting.fxml");
    }

    @FXML
    public void radixSortOnAction(ActionEvent actionEvent) {
        load("RadixSorting.fxml");
    }
}