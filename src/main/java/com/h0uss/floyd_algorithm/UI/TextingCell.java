package com.h0uss.floyd_algorithm.UI;

public class TextingCell extends Cell {

    public TextingCell() {
        super();

        setText("0");
        setEditable(true);
        getStyleClass().addAll("cell", "texting-cell");

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                setText(oldValue);
            else if (newValue.length() > 1 && newValue.charAt(0) == '0')
                setText(newValue.substring(1));
        });

        focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused && getText().isEmpty())
                setText("0");
        });
    }
}
