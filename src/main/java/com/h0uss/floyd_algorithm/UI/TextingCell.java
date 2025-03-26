package com.h0uss.floyd_algorithm.UI;

public class TextingCell extends Cell {

    public TextingCell() {
        super();

        this.setText("0");
        this.setEditable(true);
        this.getStyleClass().addAll("texting-cell", "cell");

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                this.setText(oldValue);
            }
        });
    }
}
