package com.h0uss.floyd_algorithm.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;

public class Controller{
    private static final int INITIAL_SIZE = 4;
    private static final String SUB_COLOR = "#eee";
    private static final String MAIN_COLOR = "#c6faf6";

    @FXML private AnchorPane paneAdjacency;
    @FXML private AnchorPane paneOrdinal;
    @FXML private AnchorPane paneWeight;

    @FXML private Spinner<Integer> spinnerGridSize;

    @FXML
    void initialize() {
        Matrix adjacencyMatrix = new Matrix(INITIAL_SIZE, paneAdjacency, MAIN_COLOR, SUB_COLOR);
        Matrix ordinalMatrix = new Matrix(INITIAL_SIZE, paneOrdinal, MAIN_COLOR, SUB_COLOR);
        Matrix weightMatrix = new Matrix(INITIAL_SIZE, paneWeight, MAIN_COLOR, SUB_COLOR);

        SetUp.setSpinnerProperty(spinnerGridSize);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, adjacencyMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, ordinalMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, weightMatrix);

    }

}
