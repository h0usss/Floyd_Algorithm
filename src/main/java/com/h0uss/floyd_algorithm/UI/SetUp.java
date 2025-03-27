package com.h0uss.floyd_algorithm.UI;

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
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            matrix.resizeMatrix(newValue + 1);
        });
    }
}
