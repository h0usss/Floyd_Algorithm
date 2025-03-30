package com.h0uss.floyd_algorithm.UI.matrix.cells;

public class TextingCell extends Cell {

    private final char inf = '∞';

    public TextingCell() {
        super();

        setUpSettings();
        setUpTextProperty();
        setUpFocusedProperty();
    }

    private void setUpSettings(){
        setEditableSettings(true);
        setText(String.valueOf(inf));
        getStyleClass().addAll("cell", "texting-cell");
    }

    private void setUpTextProperty(){
        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[\\d∞]*"))
                setText(oldValue);
            else if (newValue.length() > 1){
                if (newValue.charAt(0) == '0' || newValue.charAt(0) == inf)
                    setText(newValue.substring(1));
                else if (newValue.charAt(newValue.length() - 1) == inf)
                    setText(newValue.substring(0, newValue.length() - 1));
            }
        });
    }

    private void setUpFocusedProperty(){
        focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused && getText().isEmpty())
                setText("∞");
        });
    }
}
