package com.h0uss.floyd_algorithm.UI;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Cell extends TextField {

    public Cell() {
        super();

        this.setEditable(false);
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getStyleClass().add("cell");
    }
}
