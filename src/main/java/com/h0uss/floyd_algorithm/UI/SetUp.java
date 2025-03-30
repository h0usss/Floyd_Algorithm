package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydLine;
import com.h0uss.floyd_algorithm.UI.graph.FloydNode;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import com.h0uss.floyd_algorithm.UI.matrix.cells.Cell;
import com.h0uss.floyd_algorithm.logic.Algorithm;
import com.h0uss.floyd_algorithm.logic.FloydMatrix;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SetUp {
    private static final int RADIUS_NODE = 40;

    protected static void setSpinnerProperty(Spinner<Integer> spinner){
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 3);

        spinner.setValueFactory(factory);
        spinner.getValueFactory().setValue(3);
    }

    public static void connectionSpinnerMatrix(Spinner<Integer> spinner, Matrix matrix) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> matrix.resizeMatrix(newValue + 1));
    }

    public static void connectionChoiceBoxMatrix(Spinner<Integer> spinner, ChoiceBox<Integer> chBox) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> choiceBox(chBox, newValue));
    }

    public static void choiceBox(ChoiceBox<Integer> chBox, int size) {
        chBox.getItems().clear();
        for (int i = 1; i <= size; i++)
            chBox.getItems().add(i);
    }

    public static void button(Button btn, Matrix adjacencyMatrix, Matrix ordinalMatrix, Matrix weightMatrix) {
        btn.setOnAction(event -> {
            int[][] oMatrix = adjacencyMatrix.getMatrix();

            FloydMatrix floydMatrix = Algorithm.matrix(oMatrix);

            ordinalMatrix.set(floydMatrix.getOrdinalMatrixIncrement());
            weightMatrix.set(floydMatrix.getWeightMatrix());
        });
    }

    public static void nodeInitialize(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydNode> nodes) {
       pane.getChildren().removeAll(nodes);
       nodes.clear();

       for (int i = 0; i < adjacencyMatrix.getSize(); i++)
            nodes.add(new FloydNode(RADIUS_NODE , i + 1));

        pane.getChildren().addAll(nodes);
    }


    public static void nodeDraw(Pane pane, ArrayList<FloydNode> nodes){
        int centerX = (int) pane.getPrefWidth() / 2;
        int centerY = (int) pane.getPrefHeight() / 2;
        int radius = Math.min(centerX, centerY) - 20 - RADIUS_NODE;
        double grad = Math.toRadians(360.0 / nodes.size());

        for (int i = 0; i < nodes.size(); i++)
            nodes.get(i).setCenter(centerX + Math.cos(grad * i - Math.PI / 2) * radius,
                                   centerY + Math.sin(grad * i - Math.PI / 2) * radius);
    }

    public static void connectionSpinnerNode(Spinner<Integer> spinner, ArrayList<FloydNode> nodes, Pane pane) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue > oldValue){
                nodes.add(new FloydNode(RADIUS_NODE , nodes.size() + 1));
                pane.getChildren().add(nodes.get(nodes.size() - 1));
            }
            else{
                pane.getChildren().remove(nodes.get(nodes.size() - 1));
                nodes.remove(nodes.size() - 1);
            }

            nodeDraw(pane, nodes);
        });
    }


    public static void connectionMatrixLines(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, ArrayList<FloydNode> nodes) {
        for (Cell cell : adjacencyMatrix.getAllTextingCell())
            cell.textProperty().addListener((observable, oldValue, newValue) -> {

                updateLines(pane, adjacencyMatrix, lines, nodes);
            });
    }

    public static void updateLines(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, ArrayList<FloydNode> nodes) {

        int[][] matrix = adjacencyMatrix.getMatrix();
        pane.getChildren().removeAll(lines);
        lines.clear();

        for (int i = 0; i < adjacencyMatrix.getSize(); i++)
            for (int j = 0; j < adjacencyMatrix.getSize(); j++)
                if (i != j && matrix[i][j] != -1)
                    lines.add(new FloydLine(nodes.get(i), nodes.get(j), matrix[i][j]));

        pane.getChildren().addAll(lines);
    }
}
