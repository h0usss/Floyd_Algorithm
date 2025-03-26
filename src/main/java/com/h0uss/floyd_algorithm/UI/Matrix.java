package com.h0uss.floyd_algorithm.UI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class Matrix extends GridPane {

    private int size;
    private int fontFactor;

    public Matrix(int _size, Pane pane) {
        super();
        this.size = _size;

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
//        fillCellColor();
//        changeFontSize();
    }

    private void fillCellMatrix() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (i == j)
                    add(new Cell(), i, j);
                else if (i == 0 || j == 0)
                    add(new NumCell(Math.max(i, j)), i, j);
                else
                    add(new TextingCell(), i, j);
            }
    }

//    private void fillCellColor(){
//        for (int i = 0; i < size; i++)
//            for (int j = 0; j < size; j++){
//                Cell cell = getCell(i, j);
//
//                if (i == j)
//                    cell.colorIt(secondaryColor);
//                else if (i == 0 || j == 0)
//                    cell.colorIt(mainColor);
//            }
//    }

    private void changeFontSize() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                getCell(i, j).changeFontSize(fontFactor * size);
    }

    private Cell getCell(int column, int row) {
        return (Cell) this.getChildren().get(row * this.getRowCount() + column);
    }


    public void setFontFactor(int fontFactor) {
    }
}
