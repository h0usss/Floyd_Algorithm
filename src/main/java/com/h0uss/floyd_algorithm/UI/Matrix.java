package com.h0uss.floyd_algorithm.UI;

import com.h0uss.floyd_algorithm.UI.cells.Cell;
import com.h0uss.floyd_algorithm.UI.cells.NumCell;
import com.h0uss.floyd_algorithm.UI.cells.TextingCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Matrix extends GridPane {

    private int size;
    private boolean isEditable;

    public Matrix(int _size, Pane pane, boolean isEditable) {
        super();
        this.size = _size;
        this.isEditable = isEditable;

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
                else{
                    cell = new TextingCell();
                    cell.setEditableSettings(isEditable);
                }

                add(cell, i, j);
            }

        setStyles();
    }

    private Cell getCell(int column, int row) {
        return (Cell) this.getChildren().get(row * this.getRowCount() + column);
    }

    private void setStyles() {
        setGridLinesVisible(false);
        setGridLinesVisible(true);
    }

    private void setSettings(){
        setLayoutX(33);
        setLayoutY(125);

        setPrefHeight(400);
        setPrefWidth(400);
    }

    public int[][] getMatrix() {
        int[][] res = new int[size - 1][size - 1];
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - 1; j++){
                if (i == j)
                    res[i][j] = -1;
                else{
                    Cell cell = getCell(i + 1, j + 1);
                    if (cell.getText().equals("∞"))
                        res[i][j] = -1;
                    else
                        res[i][j] = Integer.parseInt(cell.getText());
                }
            }

        return res;
    }

    public void set(int[][] matrix) {
        for (int i = 1; i < size; i++)
            for (int j = 1; j < size; j++)
                if (i != j)
                    getCell(i,j).setText(String.valueOf(matrix[i-1][j-1]));
    }
}
