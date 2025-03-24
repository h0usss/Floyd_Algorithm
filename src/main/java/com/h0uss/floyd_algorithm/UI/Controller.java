package com.h0uss.floyd_algorithm.UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class Controller{
    private static final int INITIAL_SIZE = 4;

    @FXML private GridPane adjacencyMatrix;
    @FXML private GridPane weightMatrix;
    @FXML private GridPane ordinalMatrix;

    @FXML private Tab tabAdjacency;
    @FXML private Tab tabOrdinal;
    @FXML private Tab tabWeight;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML private Spinner<Integer> spinnerGridSize;

    @FXML
    void initialize() {
        SetUp.spinnerGridSize(spinnerGridSize);

        SetUp.grid(adjacencyMatrix);
        SetUp.grid(weightMatrix);
        SetUp.grid(ordinalMatrix);

    }

}
