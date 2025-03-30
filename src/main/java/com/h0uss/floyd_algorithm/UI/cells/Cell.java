package com.h0uss.floyd_algorithm.UI.cells;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;

public class Cell extends TextField {

    public Cell() {
        super();

        setAlignment(Pos.CENTER);
        setCursor(Cursor.DEFAULT);
        setEditableSettings(false);
        getStyleClass().add("cell");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public void setEditableSettings(boolean edit){
        if (edit){
            setEditable(true);
            setCursor(Cursor.TEXT);
        }
        else{
            setEditable(false);
            setCursor(Cursor.DEFAULT);
        }
    }
}
