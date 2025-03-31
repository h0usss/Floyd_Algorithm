package com.h0uss.floyd_algorithm.UI.graph;

import com.h0uss.floyd_algorithm.logic.Geometry;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

public class FloydLine extends StackPane {

    private QuadCurve line;
    private Polygon arrow;
    private Label label;
    private int numOut;
    private int numIn;

    public FloydLine(FloydNode nodeStart, FloydNode nodeEnd, int weight) {
        line = new QuadCurve();
        label = new Label(String.valueOf(weight));
        arrow = new Polygon();
        numOut = nodeStart.getNumber();
        numIn = nodeEnd.getNumber();

//        line.setStroke(Color.BLACK);
//        line.setFill(Color.TRANSPARENT);

        setPanePosition(nodeStart, nodeEnd);
        setLineSettings(nodeStart, nodeEnd);
        setTextSettings();
//        setArrowPosition(nodeStart, nodeEnd);
        setViewOrder(3);
        getChildren().addAll(line, label);
    }

    private void setPanePosition(FloydNode nodeStart, FloydNode nodeEnd){
        double minX = Math.min(nodeStart.getCenterX(), nodeEnd.getCenterX());
        double minY = Math.min(nodeStart.getCenterY(), nodeEnd.getCenterY());
        double maxX = Math.max(nodeStart.getCenterX(), nodeEnd.getCenterX());
        double maxY = Math.max(nodeStart.getCenterY(), nodeEnd.getCenterY());
        double radius = nodeStart.getRadius();

        if ((maxX - minX) < radius / 2 || (maxY - minY) < radius / 2) {
            minX = minX - radius * 2;
            maxX = maxX + radius * 2;
            minY = minY - radius * 2;
            maxY = maxY + radius * 2;
        }

        setLayoutX(minX);
        setLayoutY(minY);

        setPrefWidth(maxX - minX);
        setPrefHeight(maxY - minY);
    }

    public void setLineSettings(FloydNode nodeStart, FloydNode nodeEnd) {
        double startX = nodeStart.getCenterX() - getLayoutX();
        double startY = nodeStart.getCenterY() - getLayoutY();
        double endX = nodeEnd.getCenterX() - getLayoutX();
        double endY = nodeEnd.getCenterY() - getLayoutY();

        line.setStartX(startX);
        line.setStartY(startY);
        line.setControlX((startX + endX) / 2);
        line.setControlY((startY + endY) / 2);
        line.setEndX(endX);
        line.setEndY(endY);

        setStyleStandard();
    }

    public void setStyleStandard(){
        line.getStyleClass().removeAll("floyd-line-highlight");
        line.getStyleClass().add("floyd-line");
    }

    public void setStyleHighlight(){
        line.getStyleClass().removeAll("floyd-line");
        line.getStyleClass().add("floyd-line-highlight");
    }

    private void setTextSettings() {
        label.getStyleClass().add("floyd-line-label");
    }

    private void setArrowPosition(FloydNode nodeStart, FloydNode nodeEnd) {
        Geometry g = new Geometry();

//        arrow = g.getArrow(nodeStart.getCenterX(), nodeStart.getCenterY(),
//                nodeEnd.getCenterX(), nodeEnd.getCenterY(), nodeStart.getRadius());
    }

    public void changeCurvature(int rad){
        Geometry g = new Geometry();
        Point2D coords = g.getMiddleCoordinateForLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), 100);
        int coefficient = 1;

        if (Math.abs(line.getStartX() - line.getEndX()) < (rad / 2.0)
                || Math.abs(line.getStartY() - line.getEndY()) < (rad / 2.0)){

            setTranslateX((getPrefWidth()  / 2 - coords.getX()) / -4);             // костыль
            setTranslateY((getPrefHeight() / 2 - coords.getY()) / -4);
            coefficient = 2;
        }

        moveLabel(coords, coefficient);
        line.setControlX(coords.getX());
        line.setControlY(coords.getY());
    }

//                                            костыль
    private void moveLabel(Point2D coords, int coefficient){
        double centerX = getLayoutX() + getPrefWidth() / 2;
        double centerY = getLayoutY() + getPrefHeight() / 2;

        label.setTranslateX((centerX - coords.getX() - getLayoutX() ) / (-2 * coefficient));
        label.setTranslateY((centerY - coords.getY() - getLayoutY() ) / (-2 * coefficient));
    }

    public void setNoArrow() {
        getChildren().remove(arrow);
    }

    public int getNumOut(){
        return numOut;
    }

    public int getNumIn(){
        return numIn;
    }
}
