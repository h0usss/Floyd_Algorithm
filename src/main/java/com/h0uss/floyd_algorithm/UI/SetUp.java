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

    public static void setMatrixProperty(Matrix matrix, String mainColor, String secondaryColor, int fontFactor) {
        matrix.setLayoutX(33);
        matrix.setLayoutY(125);

        matrix.setPrefHeight(400);
        matrix.setPrefWidth(400);

        matrix.setGridLinesVisible(true);

        matrix.setStyle(
                "-main-color: " + mainColor + "; " +
                "-sec-color: " + secondaryColor + ";"
        );
        matrix.setFontFactor(fontFactor);
    }
}
