package com.h0uss.floyd_algorithm.UI.graph;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

public class FloydLine extends StackPane {

    private Label label;
    private Line line;

    public FloydLine(FloydNode nodeStart, FloydNode nodeEnd, int weight) {
        line = new Line();
        label = new Label();

        this.getChildren().addAll(line, label);

        setLinePosition(nodeStart, nodeEnd);
        setLabelText(String.valueOf(weight));
    }

    public void setLinePosition(FloydNode nodeStart, FloydNode nodeEnd) {

        setPanePosition(nodeStart, nodeEnd);

        line.setStartX(nodeStart.getCenterX());
        line.setStartY(nodeStart.getCenterY());
        line.setEndX(nodeEnd.getCenterX());
        line.setEndY(nodeEnd.getCenterY());
    }

    public void setLabelText(String weight) {
        label.setText(weight);
        label.getStyleClass().add("floyd-line-label");
    }

    private void setPanePosition(FloydNode nodeStart, FloydNode nodeEnd){
        double minX = Math.min(nodeStart.getCenterX(), nodeEnd.getCenterX());
        double minY = Math.min(nodeStart.getCenterY(), nodeEnd.getCenterY());
        double maxX = Math.max(nodeStart.getCenterX(), nodeEnd.getCenterX());
        double maxY = Math.max(nodeStart.getCenterY(), nodeEnd.getCenterY());

        setLayoutX(minX);
        setLayoutY(minY);

        setPrefWidth(maxX - minX);
        setPrefHeight(maxY - minY);
    }
}
