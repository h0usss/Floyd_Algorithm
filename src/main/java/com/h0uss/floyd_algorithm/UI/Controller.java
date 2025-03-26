package com.h0uss.floyd_algorithm.UI;

//import java.net.URL;
//import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;

public class Controller{
    private static final int INITIAL_SIZE = 4;
    private static final int FONT_FACTOR = 8;
    private static final String SECONDARY_COLOR = "#eee";
    private static final String MAIN_COLOR = "#c6faf6";

    @FXML private AnchorPane paneAdjacency;
    @FXML private AnchorPane paneOrdinal;
    @FXML private AnchorPane paneWeight;

//    @FXML private ResourceBundle resources;
//    @FXML private URL location;

    @FXML private Spinner<Integer> spinnerGridSize;

    @FXML
    void initialize() {
        Matrix adjacencyMatrix = new Matrix(INITIAL_SIZE, paneAdjacency);
        Matrix ordinalMatrix = new Matrix(INITIAL_SIZE, paneOrdinal);
        Matrix weightMatrix = new Matrix(INITIAL_SIZE, paneWeight);

        SetUp.setMatrixProperty(adjacencyMatrix, MAIN_COLOR, SECONDARY_COLOR, FONT_FACTOR);
        SetUp.setMatrixProperty(ordinalMatrix, MAIN_COLOR, SECONDARY_COLOR, FONT_FACTOR);
        SetUp.setMatrixProperty(weightMatrix, MAIN_COLOR, SECONDARY_COLOR, FONT_FACTOR);

        SetUp.setSpinnerProperty(spinnerGridSize);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, adjacencyMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, ordinalMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, weightMatrix);

    }

}
