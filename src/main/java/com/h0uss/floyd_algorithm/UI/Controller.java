package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydLine;
import com.h0uss.floyd_algorithm.UI.graph.FloydNode;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;


public class Controller{

    private static final int INITIAL_SIZE = 4;
    private static ArrayList<FloydNode> nodes = new ArrayList<>();
    private static ArrayList<FloydLine> lines = new ArrayList<>();

    @FXML private AnchorPane paneAdjacency;
    @FXML private AnchorPane paneOrdinal;
    @FXML private AnchorPane paneWeight;
    @FXML private AnchorPane paneDraw;

    @FXML private Button btnCalc;

    @FXML private ChoiceBox<Integer> chBoxTo;
    @FXML private ChoiceBox<Integer> chBoxFrom;

    @FXML private Spinner<Integer> spinnerGridSize;

    @FXML
    void initialize() {
        Matrix adjacencyMatrix = new Matrix(INITIAL_SIZE, paneAdjacency, true);
        Matrix ordinalMatrix = new Matrix(INITIAL_SIZE, paneOrdinal, false);
        Matrix weightMatrix = new Matrix(INITIAL_SIZE, paneWeight, false);

        SetUp.setSpinnerProperty(spinnerGridSize);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, adjacencyMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, ordinalMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, weightMatrix);

        SetUp.choiceBox(chBoxFrom, INITIAL_SIZE);
        SetUp.choiceBox(chBoxTo, INITIAL_SIZE);
        SetUp.connectionChoiceBoxMatrix(spinnerGridSize, chBoxFrom);
        SetUp.connectionChoiceBoxMatrix(spinnerGridSize, chBoxTo);

        SetUp.button(btnCalc, adjacencyMatrix, ordinalMatrix, weightMatrix);

        SetUp.nodeInitialize(paneDraw, adjacencyMatrix, nodes);
        SetUp.nodeDraw(paneDraw, nodes);
        SetUp.connectionSpinnerNode(spinnerGridSize, nodes, paneDraw);

        SetUp.connectionMatrixLines(paneDraw, adjacencyMatrix, lines, nodes);
    }
}
