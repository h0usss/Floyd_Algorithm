package com.h0uss.floyd_algorithm.UI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Matrix extends GridPane {

    private int size;

    private String mainColor;
    private String subColor;

    public Matrix(int _size, Pane pane, String mainColor, String subColor) {
        super();
        this.size = _size;
        this.subColor = subColor;
        this.mainColor = mainColor;

        setSettings();

        pane.getChildren().add(this);

        resizeMatrix(size);
    }

    public void resizeMatrix(int _size) {
        size = _size;

        this.getChildren().clear();
        this.getColumnConstraints().clear();
        this.getRowConstraints().clear();

        for (int i = 0; i < size; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0/size);
            this.getColumnConstraints().add(col);

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0/size);
            this.getRowConstraints().add(row);
        }

        fillCellMatrix();
        setStyles();

        requestLayout();
    }

    private void fillCellMatrix() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                Cell cell;

                if (i == j)
                    cell = new Cell();
                else if (i == 0 || j == 0)
                    cell = new NumCell(Math.max(i, j));
                else
                    cell = new TextingCell();

                add(cell, i, j);
            }

        setStyles();
    }

    private Cell getCell(int column, int row) {
        return (Cell) this.getChildren().get(row * this.getRowCount() + column);
    }

    private void setStyles() {
        setStyle(
                "-main-color: " + mainColor + "; " +
                "-sec-color: " + subColor + ";"
        );
        setGridLinesVisible(false);
        setGridLinesVisible(true);
    }

    private void setSettings(){
        setLayoutX(33);
        setLayoutY(125);

        setPrefHeight(400);
        setPrefWidth(400);
    }
}
