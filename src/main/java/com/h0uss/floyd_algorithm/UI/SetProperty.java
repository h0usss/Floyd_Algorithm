package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydArrayNodes;
import com.h0uss.floyd_algorithm.UI.graph.FloydLine;
import com.h0uss.floyd_algorithm.UI.graph.FloydNode;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import com.h0uss.floyd_algorithm.UI.matrix.cells.Cell;
import com.h0uss.floyd_algorithm.logic.Algorithm;
import com.h0uss.floyd_algorithm.logic.FloydMatrix;
import com.h0uss.floyd_algorithm.util.Property;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SetProperty {

    private final int nodeRadius;
    private final int matrixMinSize;
    private final int matrixMaxSize;

    public SetProperty(){
        Property config = new Property();

        nodeRadius = config.getProperty("node.radius");
        matrixMinSize = config.getProperty("matrix.min.size");
        matrixMaxSize = config.getProperty("matrix.max.size");
    }


    protected void spinner(Spinner<Integer> spinner){
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(matrixMinSize, matrixMaxSize, matrixMinSize);

        spinner.setValueFactory(factory);
        spinner.getValueFactory().setValue(matrixMinSize);
    }

    public void choiceBox(ChoiceBox<Integer> chBox, int size) {
        chBox.getItems().clear();
        for (int i = 1; i <= size; i++)
            chBox.getItems().add(i);
    }

    public void btnReset(Spinner<Integer> spinner, Button btn, Matrix aMatrix) {
        btn.setOnAction(event -> {
            spinner.getValueFactory().setValue(aMatrix.getSize() + 1);
            spinner.getValueFactory().setValue(aMatrix.getSize() - 1);
        });
    }

    public  void buttonCalc(Button btn, Label label, ChoiceBox<Integer> chBoxFrom, ChoiceBox<Integer> chBoxTo,
                                  Matrix adjacencyMatrix, Matrix ordinalMatrix, Matrix weightMatrix,
                                  FloydArrayNodes nodes, ArrayList<FloydLine> lines) {
        btn.setOnAction(event -> {
            int[][] oMatrix = adjacencyMatrix.getMatrix();
            ArrayList<Integer> path;

            FloydMatrix floydMatrix = Algorithm.mainAlgorithm(oMatrix);

            ordinalMatrix.set(floydMatrix.getOrdinalMatrix());
            weightMatrix.set(floydMatrix.getWeightMatrix());

            resetColorNodes(nodes);
            resetColorLines(lines);

            if (chBoxFrom.getValue() != null && chBoxTo.getValue() != null) {
                path = Algorithm.findShortestPath(chBoxFrom.getValue(), chBoxTo.getValue(), floydMatrix);

                if (path.isEmpty()){
                    label.setText("Нет пути");
                    return;
                }

                StringBuilder text = new StringBuilder();

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

    private  void resetColorNodes(FloydArrayNodes nodes) {
        for (FloydNode node : nodes)
            node.setStyleStandard();
    }

    private  void resetColorLines(ArrayList<FloydLine> lines) {
        for (FloydLine line : lines)
            line.setStyleStandard();
    }

    private  void highlightNodes(FloydArrayNodes nodes, List<Integer> path) {
        for (FloydNode node : nodes)
            if (path.contains(node.getNumber()))
                node.setStyleHighlight();
    }

    private  void highlightLines(ArrayList<FloydLine> lines, List<Integer> path) {
        for (int i = 0; i < path.size() - 1; i++)
            for (FloydLine line : lines)
                if (line.getNumOut() == path.get(i) && line.getNumIn() == path.get(i + 1)){
                    line.setStyleHighlight();
                    break;
                }
    }


    public void nodes(Pane pane, FloydArrayNodes nodes) {
        pane.getChildren().addAll(nodes);
    }

    public void dragNodeAndLine(FloydArrayNodes nodes, ArrayList<FloydLine> lines, Matrix aMatrix, Pane pane) {
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

    public  void connectionSpinnerNode(Spinner<Integer> spinner, FloydArrayNodes nodes, Pane pane, ArrayList<FloydLine> lines, Matrix aMatrix) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue > oldValue){
                nodes.add(new FloydNode(nodeRadius , nodes.size() + 1));
                pane.getChildren().add(nodes.get(nodes.size() - 1));
            }
            else{
                pane.getChildren().remove(nodes.get(nodes.size() - 1));
                nodes.remove(nodes.size() - 1);
            }

            nodes.drawNodesToStartPosition();
            resetColorNodes(nodes);
            dragNodeAndLine(nodes, lines, aMatrix, pane);
        });
    }

    public  void connectionSpinnerLines(Spinner<Integer> spinner,  AnchorPane pane, Matrix adjacencyMatrix,
                                        ArrayList<FloydLine> lines, FloydArrayNodes nodes) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            pane.getChildren().removeAll(lines);
            connectionMatrixLines(pane, adjacencyMatrix, lines, nodes);
        });
    }

    public  void connectionMatrixLines(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, FloydArrayNodes nodes) {
        for (Cell cell : adjacencyMatrix.getAllTextingCell())
            cell.textProperty().addListener((observable, oldValue, newValue)
                    -> updateLines(pane, adjacencyMatrix, lines, nodes));
    }


    public  void updateLines(Pane pane, Matrix adjacencyMatrix, ArrayList<FloydLine> lines, FloydArrayNodes nodes) {

        List<Pair<Integer, Integer>> highlightedLines = new ArrayList<>();
        for (FloydLine line : lines)
            if (line.isHighlighted())
                highlightedLines.add(new Pair<>(line.getNumOut(), line.getNumIn()));

        int[][] matrix = adjacencyMatrix.getMatrix();
        pane.getChildren().removeAll(lines);
        lines.clear();

        for (int i = 0; i < adjacencyMatrix.getSize(); i++) {
            for (int j = i + 1; j < adjacencyMatrix.getSize(); j++) {
                int weightIJ = matrix[i][j];
                int weightJI = matrix[j][i];

                if (weightIJ == -1 && weightJI == -1) continue;

                FloydLine line;
                if (weightJI == -1) {
                    line = new FloydLine(nodes.get(i), nodes.get(j), weightIJ);
                } else if (weightIJ == -1) {
                    line = new FloydLine(nodes.get(j), nodes.get(i), weightJI);
                } else if (weightIJ == weightJI) {
                    line = new FloydLine(nodes.get(i), nodes.get(j), weightIJ);
                    line.setNoArrow();
                } else {
                    line = new FloydLine(nodes.get(i), nodes.get(j), matrix[i][j]);
                    line.changeCurvature(nodeRadius);
                    FloydLine line2 = new FloydLine(nodes.get(j), nodes.get(i), matrix[j][i]);
                    line2.changeCurvature(nodeRadius);
                    lines.add(line2);
                }

                if (highlightedLines.contains(new Pair<>(line.getNumOut(), line.getNumIn()))) {
                    line.setStyleHighlight();
                }
                lines.add(line);
            }
        }

        pane.getChildren().removeAll(lines);
        pane.getChildren().addAll(lines);
    }
}
