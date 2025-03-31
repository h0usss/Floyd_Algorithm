package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydLine;
import com.h0uss.floyd_algorithm.UI.graph.FloydNode;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import com.h0uss.floyd_algorithm.UI.matrix.cells.Cell;
import com.h0uss.floyd_algorithm.logic.Algorithm;
import com.h0uss.floyd_algorithm.logic.FloydMatrix;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

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


    public static void btnReset(Button btn, Label label, ChoiceBox<Integer> chBoxFrom, ChoiceBox<Integer> chBoxTo, Matrix aMatrix,
                                Matrix oMatrix, Matrix wMatrix, ArrayList<FloydLine> lines) {
        btn.setOnAction(event -> {
            aMatrix.clear();
            oMatrix.clear();
            wMatrix.clear();
            lines.clear();
            chBoxFrom.setValue(null);
            chBoxTo.setValue(null);
            label.setText("");
        });
    }

    public static void buttonCalc(Button btn, Label label, ChoiceBox<Integer> chBoxFrom, ChoiceBox<Integer> chBoxTo,
                                  Matrix adjacencyMatrix, Matrix ordinalMatrix, Matrix weightMatrix,
                                  ArrayList<FloydNode> nodes, ArrayList<FloydLine> lines) {
        btn.setOnAction(event -> {
            int[][] oMatrix = adjacencyMatrix.getMatrix();
            ArrayList<Integer> path;

            FloydMatrix floydMatrix = Algorithm.mainAlgorithm(oMatrix);

            ordinalMatrix.set(floydMatrix.getOrdinalMatrixIncrement());
            weightMatrix.set(floydMatrix.getWeightMatrix());

            resetColorNodes(nodes);
            resetColorLines(lines);

            if (chBoxFrom.getValue() != null && chBoxTo.getValue() != null) {
                path = Algorithm.findShortestPath(chBoxFrom.getValue(), chBoxTo.getValue(), floydMatrix);

                if (path.isEmpty()){
                    label.setText("Нет пути");
                    return;
                }

                StringBuffer text = new StringBuffer();

                for (int i = 0; i < path.size(); i++){
                    if (i == 0)
                        text.append("Вес: ").append(path.get(i)).append(" ; Путь: ");
                    else if (i == path.size() - 1)
                        text.append(path.get(i));
                    else
                        text.append(path.get(i)).append(" > ");
                }
                label.setText(text.toString());

                highlightNodes(nodes, path.subList(1, path.size()));
                highlightLines(lines, path.subList(1, path.size()));
            }
        });
    }

    private static void resetColorNodes(ArrayList<FloydNode> nodes) {
        for (FloydNode node : nodes)
            node.setStyleStandard();
    }

    private static void resetColorLines(ArrayList<FloydLine> lines) {
        for (FloydLine line : lines)
            line.setStyleStandard();
    }

    private static void highlightNodes(ArrayList<FloydNode> nodes, List<Integer> path) {
        for (FloydNode node : nodes)
            if (path.contains(node.getNumber()))
                node.setStyleHighlight();
    }

    private static void highlightLines(ArrayList<FloydLine> lines, List<Integer> path) {
        for (int i = 0; i < path.size() - 1; i++)
            for (FloydLine line : lines)
                if (line.getNumOut() == path.get(i) && line.getNumIn() == path.get(i + 1)){
                    line.setStyleHighlight();
                    break;
                }
    }

    public static void connectionSpinnerLabel(Spinner<Integer> spinner, Label label) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> label.setText(""));
    }

    public static void initializeNode(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydNode> nodes) {
       pane.getChildren().removeAll(nodes);
       nodes.clear();

       for (int i = 0; i < adjacencyMatrix.getSize(); i++)
            nodes.add(new FloydNode(RADIUS_NODE , i + 1));

        pane.getChildren().addAll(nodes);
    }


    public static void drawNode(Pane pane, ArrayList<FloydNode> nodes){
        double centerX = pane.getPrefWidth() / 2;
        double centerY = pane.getPrefHeight() / 2;
        double radius = Math.min(centerX, centerY) - 20 - RADIUS_NODE;
        double grad = Math.toRadians(360.0 / nodes.size());

        for (int i = 0; i < nodes.size(); i++)
            nodes.get(i).setCenter(centerX + Math.cos(grad * i - Math.PI / 2) * radius,
                                   centerY + Math.sin(grad * i - Math.PI / 2) * radius);
    }

    public static void dragNodeAndLine(ArrayList<FloydNode> nodes, ArrayList<FloydLine> lines, Matrix aMatrix, Pane pane) {
        for (FloydNode node : nodes){
            node.setOnMouseDragged(null);

            node.setOnMouseDragged(event -> {
                double newX = event.getSceneX() - node.getBoundsInLocal().getWidth()/2;
                double newY = event.getSceneY() - node.getBoundsInLocal().getHeight()/2;

                newX = Math.max(0, Math.min(newX, pane.getWidth() - node.getBoundsInLocal().getWidth()));
                newY = Math.max(0, Math.min(newY, pane.getHeight() - node.getBoundsInLocal().getHeight()));

                node.setLayoutX(newX);
                node.setLayoutY(newY);

                updateLines(pane, aMatrix, lines, nodes);
            });
        }
    }

    public static void connectionSpinnerNode(Spinner<Integer> spinner, ArrayList<FloydNode> nodes, Pane pane, ArrayList<FloydLine> lines, Matrix aMatrix) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue > oldValue){
                nodes.add(new FloydNode(RADIUS_NODE , nodes.size() + 1));
                pane.getChildren().add(nodes.get(nodes.size() - 1));
            }
            else{
                pane.getChildren().remove(nodes.get(nodes.size() - 1));
                nodes.remove(nodes.size() - 1);
            }

            drawNode(pane, nodes);
            resetColorNodes(nodes);
            dragNodeAndLine(nodes, lines, aMatrix, pane);
        });
    }


    public static void connectionMatrixLines(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, ArrayList<FloydNode> nodes) {
        for (Cell cell : adjacencyMatrix.getAllTextingCell())
            cell.textProperty().addListener((observable, oldValue, newValue) -> {
                updateLines(pane, adjacencyMatrix, lines, nodes);
            });
    }

    public static void connectionSpinnerLines(Spinner<Integer> spinner,  AnchorPane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, ArrayList<FloydNode> nodes) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            pane.getChildren().removeAll(lines);
            connectionMatrixLines(pane, adjacencyMatrix, lines, nodes);
        });
    }

    public static void updateLines(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, ArrayList<FloydNode> nodes) {

        int[][] matrix = adjacencyMatrix.getMatrix();
        pane.getChildren().removeAll(lines);
        lines.clear();

        for (int i = 0; i < adjacencyMatrix.getSize(); i++)
            for (int j = i + 1; j < adjacencyMatrix.getSize(); j++){
                int weightIJ = matrix[i][j];
                int weightJI = matrix[j][i];

                if (weightIJ == -1 && weightJI == -1)
                    continue;
                else if (weightJI == -1)
                    lines.add(new FloydLine(nodes.get(i), nodes.get(j), weightIJ));
                else if (weightIJ == -1)
                    lines.add(new FloydLine(nodes.get(j), nodes.get(i), weightJI));
                else if (weightIJ == weightJI) {
                    FloydLine line = new FloydLine(nodes.get(i), nodes.get(j), weightIJ);
                    line.setNoArrow();
                    lines.add(line);
                }
                else {
                    FloydLine line1 = new FloydLine(nodes.get(i), nodes.get(j), matrix[i][j]);
                    line1.changeCurvature(RADIUS_NODE);
                    lines.add(line1);

                    FloydLine line2 = new FloydLine(nodes.get(j), nodes.get(i), matrix[j][i]);
                    line2.changeCurvature(RADIUS_NODE);
                    lines.add(line2);
                }
            }

        redrawLines(pane, nodes, lines);
    }

    private static void redrawLines(Pane pane, ArrayList<FloydNode> nodes, ArrayList<FloydLine> lines){
        pane.getChildren().removeAll(lines);
        pane.getChildren().addAll(lines);
    }
}
