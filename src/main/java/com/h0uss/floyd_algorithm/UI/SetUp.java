package com.h0uss.floyd_algorithm.UI;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class SetUp {

    protected static void spinnerGridSize(Spinner<Integer> spinner){
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 50, 3);

        spinner.setValueFactory(factory);
        spinner.getValueFactory().setValue(3);
    }

    public static void grid(GridPane grid) {
        grid.setLayoutX(33);
        grid.setLayoutY(125);

        grid.prefHeight(400);
        grid.prefWidth(400);

        grid.setStyle("-fx-background-color: #eee");

        grid.setDisable(false);
    }


}
