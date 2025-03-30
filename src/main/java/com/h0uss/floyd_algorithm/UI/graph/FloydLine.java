package com.h0uss.floyd_algorithm.UI.graph;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class FloydLine extends StackPane {

    private Polygon arrow;
    private Label label;
    private Line line;

    public FloydLine(FloydNode nodeStart, FloydNode nodeEnd, int weight) {
        line = new Line();
        label = new Label();
        arrow = new Polygon( 0, 0, 15, 5, 15, -5);

        this.getChildren().addAll(line, arrow, label);

        setLinePosition(nodeStart, nodeEnd);
        setArrowPosition(nodeStart, nodeEnd);
        setLabelText(String.valueOf(weight));
    }

    private void setArrowPosition(FloydNode nodeStart, FloydNode nodeEnd) {


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
        double radius = nodeStart.getRadius();

        if ((maxX - minX) < radius / 2 || (maxY - minY) < radius / 2) {
           minX = minX - radius / 2;
           maxX = maxX + radius / 2;
           minY = minY - radius / 2;
           maxY = maxY + radius / 2;
        }

        setLayoutX(minX);
        setLayoutY(minY);

        setPrefWidth(maxX - minX);
        setPrefHeight(maxY - minY);
    }
}
