package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.graph.FloydArrayLines;
import com.h0uss.floyd_algorithm.UI.graph.FloydArrayNodes;
import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import com.h0uss.floyd_algorithm.logic.Algorithm;
import com.h0uss.floyd_algorithm.logic.FloydMatrix;
import com.h0uss.floyd_algorithm.util.Property;
import javafx.scene.control.*;

import java.util.ArrayList;

public class SetProperty {

    private final int matrixMinSize;
    private final int matrixMaxSize;

    public SetProperty(){
        Property config = new Property();

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
                                  FloydArrayNodes nodes, FloydArrayLines lines) {
        btn.setOnAction(event -> {
            int[][] oMatrix = adjacencyMatrix.getMatrix();
            ArrayList<Integer> path;
            int weight;

            FloydMatrix floydMatrix = Algorithm.mainAlgorithm(oMatrix);

            ordinalMatrix.set(floydMatrix.getOrdinalMatrix());
            weightMatrix.set(floydMatrix.getWeightMatrix());

            nodes.setNormalColor();
            lines.setNormalColor();

            if (chBoxFrom.getValue() != null && chBoxTo.getValue() != null) {
                path = Algorithm.getShortestPath(chBoxFrom.getValue(), chBoxTo.getValue(), floydMatrix);
                weight = Algorithm.getWeightShortestPath(path, floydMatrix);

                setLabelPathText(label, path, weight);

                nodes.setHighlightColor(path);
                lines.setHighlightColor(path);
            }
        });
    }

    private void setLabelPathText(Label label, ArrayList<Integer> path, int weight){
        if (path.isEmpty()){
            label.setText("No way");
            return;
        }

        StringBuilder text = new StringBuilder();
        text.append("Weight: ").append(weight).append(" ; Path: ");

        for (int i = 0; i < path.size(); i++){
            if (i == path.size() - 1)
                text.append(path.get(i));
            else
                text.append(path.get(i)).append(" > ");
        }

        label.setText(text.toString());
    }


}
