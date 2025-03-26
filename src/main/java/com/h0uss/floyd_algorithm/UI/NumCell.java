package com.h0uss.floyd_algorithm.UI;

public class NumCell extends Cell {

    public NumCell(int num) {
        super();

        this.setText(String.valueOf(num));
        this.getStyleClass().addAll("num-cell", "cell");
    }
}
