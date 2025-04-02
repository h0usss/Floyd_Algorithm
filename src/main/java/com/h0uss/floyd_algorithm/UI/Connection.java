package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class Connection {

    public void spinnerMatrix(Spinner<Integer> spinner, Matrix matrix) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> matrix.resizeMatrix(newValue + 1));
    }

    public void spinnerLabel(Spinner<Integer> spinner, Label label) {
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> label.setText(""));
    }

    public void choiceBoxMatrix(Spinner<Integer> spinner, ChoiceBox<Integer> chBox) {
        SetProperty setProperty = new SetProperty();
        spinner.valueProperty().addListener((observable, oldValue, newValue)
                -> setProperty.choiceBox(chBox, newValue));
    }
}
