package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.logic.Algorithm;
import com.h0uss.floyd_algorithm.logic.FloydMatrix;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SetUp {

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
}
