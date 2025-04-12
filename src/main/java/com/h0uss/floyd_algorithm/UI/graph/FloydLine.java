package com.h0uss.floyd_algorithm.UI.graph;

import com.h0uss.floyd_algorithm.util.Geometry;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

public class FloydLine extends StackPane {

    private final QuadCurve line;
    private final Polygon arrow;
    private final Label label;
    private final int numOut;
    private final int numIn;
    private final FloydNode nodeS;
    private final FloydNode nodeE;
    private boolean isHighlighted = false;

    public FloydLine(FloydNode nodeStart, FloydNode nodeEnd, int weight) {
        line = new QuadCurve();
        label = new Label(String.valueOf(weight));
        arrow = new Polygon();
        numOut = nodeStart.getNumber();
        numIn = nodeEnd.getNumber();
        nodeS = nodeStart;
        nodeE = nodeEnd;

        Pane linePane = new Pane();
        linePane.getChildren().add(line);

        setPanePosition(nodeStart, nodeEnd);
        setLineSettings(nodeStart, nodeEnd);
        setTextSettings();
        setArrowPosition(nodeStart, nodeEnd, 0);
        setViewOrder(3);

        getChildren().addAll(linePane, label, arrow);
    }

    private void setPanePosition(FloydNode nodeStart, FloydNode nodeEnd){
        double minX = Math.min(nodeStart.getCenterX(), nodeEnd.getCenterX());
        double minY = Math.min(nodeStart.getCenterY(), nodeEnd.getCenterY());
        double maxX = Math.max(nodeStart.getCenterX(), nodeEnd.getCenterX());
        double maxY = Math.max(nodeStart.getCenterY(), nodeEnd.getCenterY());
        double radius = nodeStart.getRadius();

        if ((maxX - minX) <= radius || (maxY - minY) <= radius) {
            minX = minX - radius * 1.5;
            maxX = maxX + radius * 1.5;
            minY = minY - radius * 1.5;
            maxY = maxY + radius * 1.5;
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

        arrow.getStyleClass().removeAll("floyd-line-arrow-highlight");
        arrow.getStyleClass().add("floyd-line-arrow");

        isHighlighted = false;
    }

    public void setStyleHighlight(){
        line.getStyleClass().removeAll("floyd-line");
        line.getStyleClass().add("floyd-line-highlight");

        arrow.getStyleClass().removeAll("floyd-line-arrow");
        arrow.getStyleClass().add("floyd-line-arrow-highlight");

        isHighlighted = true;
    }

    private void setTextSettings() {
        label.getStyleClass().add("floyd-line-label");
    }

    private void setArrowPosition(FloydNode nodeStart, FloydNode nodeEnd, double height) {
        double arrowHeadSize = 10;
        double startX = nodeStart.getCenterX() - getLayoutX();
        double startY = nodeStart.getCenterY() - getLayoutY();
        double endX = nodeEnd.getCenterX() - getLayoutX();
        double endY = nodeEnd.getCenterY() - getLayoutY();

        Point2D control = Geometry.getMiddleCoordinateForLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), height);
        double controlX = control.getX();
        double controlY = control.getY();

        double t = 0.97;
        double dx = (1 - t) * (controlX - startX) + t * (endX - controlX);
        double dy = (1 - t) * (controlY - startY) + t * (endY - controlY);
        double angle = Math.atan2(dy, dx);

        Double[] points = new Double[]{
                0.0, 0.0,
                -arrowHeadSize, -arrowHeadSize / 2,
                -arrowHeadSize, arrowHeadSize / 2
        };

        arrow.getPoints().clear();
        arrow.getPoints().addAll(points);

        arrow.setTranslateX(endX - getPrefWidth() / 2 - (nodeEnd.getRadius() + 3) * Math.cos(angle));
        arrow.setTranslateY(endY - getPrefHeight() / 2 - (nodeEnd.getRadius() + 3) * Math.sin(angle));

        arrow.setRotate(Math.toDegrees(angle));
    }

    public void changeCurvature(){
        Point2D coords = Geometry.getMiddleCoordinateForLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), 100);

        moveLabel(coords);
        setArrowPosition(nodeS, nodeE, 100);
        line.setControlX(coords.getX());
        line.setControlY(coords.getY());
    }

    private void moveLabel(Point2D coords){
        double centerX = getLayoutX() + getPrefWidth() / 2;
        double centerY = getLayoutY() + getPrefHeight() / 2;

        label.setTranslateX((centerX - coords.getX() - getLayoutX() ) / -2);
        label.setTranslateY((centerY - coords.getY() - getLayoutY() ) / -2);
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

    public boolean isHighlighted() {
        return isHighlighted;
    }
}
