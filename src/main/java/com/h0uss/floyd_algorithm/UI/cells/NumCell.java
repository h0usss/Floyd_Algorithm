package com.h0uss.floyd_algorithm.UI.cells;

public class NumCell extends Cell {

    public NumCell(int num) {
        super();

        setText(String.valueOf(num));
        getStyleClass().addAll("cell", "num-cell");
    }
}
