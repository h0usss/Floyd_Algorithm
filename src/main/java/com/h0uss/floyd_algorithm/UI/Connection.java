package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydArrayLines;
import com.h0uss.floyd_algorithm.UI.graph.FloydArrayNodes;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import com.h0uss.floyd_algorithm.UI.matrix.cells.Cell;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Connection {

    public void spinnerMatrix(Spinner<Integer> spinner, Matrix matrix) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> matrix.resizeMatrix(newValue + 1));
    }

    public void spinnerLabel(Spinner<Integer> spinner, Label label) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> label.setText(""));
    }

    public void spinnerLines(Spinner<Integer> spinner, AnchorPane pane, Matrix adjacencyMatrix,
                             FloydArrayLines lines, FloydArrayNodes nodes) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            pane.getChildren().removeAll(lines);
            matrixLines(adjacencyMatrix, lines, nodes);
            lines.setNormalColor();
        });
    }

    public void spinnerNode(Spinner<Integer> spinner, FloydArrayNodes nodes, Pane pane, FloydArrayLines lines) {
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue > oldValue){
                nodes.addNewNodeByNum(nodes.size() + 1);
                pane.getChildren().add(nodes.get(nodes.size() - 1));
            }
            else{
                pane.getChildren().remove(nodes.get(nodes.size() - 1));
                nodes.remove(nodes.size() - 1);
            }

            nodes.drawNodesToStartPosition();
            nodes.setNormalColor();
            nodes.dragNodeAndLine(lines, pane);
        });
    }

    public void choiceBoxMatrix(Spinner<Integer> spinner, ChoiceBox<Integer> chBox) {
        SetProperty setProperty = new SetProperty();
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> setProperty.choiceBox(chBox, newValue));
    }

    public void matrixLines(Matrix adjacencyMatrix, FloydArrayLines lines, FloydArrayNodes nodes) {
        for (Cell cell : adjacencyMatrix.getAllTextingCell())
            cell.textProperty().addListener((observable, oldValue, newValue)
                    -> lines.update(adjacencyMatrix, nodes));
    }
}
