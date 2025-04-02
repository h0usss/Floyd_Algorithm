package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydArrayNodes;
import com.h0uss.floyd_algorithm.UI.graph.FloydLine;
import com.h0uss.floyd_algorithm.UI.graph.FloydNode;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import com.h0uss.floyd_algorithm.util.Property;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;


public class Controller{

    private int matrixDefaultSize;
    private int nodeRadius;

    private FloydArrayNodes nodes;
    private final ArrayList<FloydLine> lines = new ArrayList<>();

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
        configSetUp();
        SetProperty setProperty = new SetProperty();
        Connection connection = new Connection();

        Matrix adjacencyMatrix = new Matrix(matrixDefaultSize, paneAdjacency, true);
        Matrix ordinalMatrix = new Matrix(matrixDefaultSize, paneOrdinal, false);
        Matrix weightMatrix = new Matrix(matrixDefaultSize, paneWeight, false);

        nodes = new FloydArrayNodes(paneDraw, adjacencyMatrix, nodeRadius);

        setProperty.spinner(spinnerGridSize);
        connection.spinnerMatrix(spinnerGridSize, adjacencyMatrix);
        connection.spinnerMatrix(spinnerGridSize, ordinalMatrix);
        connection.spinnerMatrix(spinnerGridSize, weightMatrix);

        setProperty.choiceBox(chBoxFrom, matrixDefaultSize);
        setProperty.choiceBox(chBoxTo, matrixDefaultSize);
        connection.choiceBoxMatrix(spinnerGridSize, chBoxFrom);
        connection.choiceBoxMatrix(spinnerGridSize, chBoxTo);

        setProperty.buttonCalc(btnCalc, labelPath, chBoxFrom, chBoxTo, adjacencyMatrix, ordinalMatrix, weightMatrix, nodes, lines);
        connection.spinnerLabel(spinnerGridSize, labelPath);

        setProperty.btnReset(spinnerGridSize, btnReset,adjacencyMatrix);

        setProperty.nodes(paneDraw, nodes);
        setProperty.dragNodeAndLine(nodes, lines, adjacencyMatrix, paneDraw);
        setProperty.connectionSpinnerNode(spinnerGridSize, nodes, paneDraw, lines, adjacencyMatrix);

        setProperty.connectionMatrixLines(paneDraw, adjacencyMatrix, lines, nodes);
        setProperty.connectionSpinnerLines(spinnerGridSize, paneDraw, adjacencyMatrix, lines, nodes);
    }

    private void configSetUp(){
        Property config = new Property();
        matrixDefaultSize = config.getProperty("matrix.default.size");
        nodeRadius = config.getProperty("node.radius");
    }
}

// TODO: REFACTORING!!