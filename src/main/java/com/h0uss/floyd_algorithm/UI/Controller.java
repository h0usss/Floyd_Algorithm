package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydLine;
import com.h0uss.floyd_algorithm.UI.graph.FloydNode;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;


public class Controller{

    private static final int INITIAL_SIZE = 3;
    private static ArrayList<FloydNode> nodes = new ArrayList<>();
    private static ArrayList<FloydLine> lines = new ArrayList<>();

    @FXML private AnchorPane paneAdjacency;
    @FXML private AnchorPane paneOrdinal;
    @FXML private AnchorPane paneWeight;
    @FXML private AnchorPane paneDraw;

    @FXML private Button btnCalc;
    @FXML private Button btnReset;

    @FXML private ChoiceBox<Integer> chBoxTo;
    @FXML private ChoiceBox<Integer> chBoxFrom;

    @FXML private Spinner<Integer> spinnerGridSize;

    @FXML private Label labelPath;

    @FXML
    void initialize() {
        Matrix adjacencyMatrix = new Matrix(INITIAL_SIZE + 1, paneAdjacency, true);
        Matrix ordinalMatrix = new Matrix(INITIAL_SIZE + 1, paneOrdinal, false);
        Matrix weightMatrix = new Matrix(INITIAL_SIZE + 1, paneWeight, false);

        SetUp.setSpinnerProperty(spinnerGridSize);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, adjacencyMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, ordinalMatrix);
        SetUp.connectionSpinnerMatrix(spinnerGridSize, weightMatrix);

        SetUp.choiceBox(chBoxFrom, INITIAL_SIZE);
        SetUp.choiceBox(chBoxTo, INITIAL_SIZE);
        SetUp.connectionChoiceBoxMatrix(spinnerGridSize, chBoxFrom);
        SetUp.connectionChoiceBoxMatrix(spinnerGridSize, chBoxTo);

        SetUp.buttonCalc(btnCalc, labelPath, chBoxFrom, chBoxTo, adjacencyMatrix, ordinalMatrix, weightMatrix, nodes, lines);
        SetUp.connectionSpinnerLabel(spinnerGridSize, labelPath);

        SetUp.btnReset(spinnerGridSize, btnReset,adjacencyMatrix);

        SetUp.initializeNode(paneDraw, adjacencyMatrix, nodes);
        SetUp.dragNodeAndLine(nodes, lines, adjacencyMatrix, paneDraw);
        SetUp.drawNode(paneDraw, nodes);
        SetUp.connectionSpinnerNode(spinnerGridSize, nodes, paneDraw, lines, adjacencyMatrix);

        SetUp.connectionMatrixLines(paneDraw, adjacencyMatrix, lines, nodes);
        SetUp.connectionSpinnerLines(spinnerGridSize, paneDraw, adjacencyMatrix, lines, nodes);
    }
}

// TODO: REFACTORING!!