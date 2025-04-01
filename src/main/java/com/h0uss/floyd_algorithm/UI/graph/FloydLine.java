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
    private FloydNode nodeS;
    private FloydNode nodeE;
    private boolean isHighlighted = false;

    public FloydLine(FloydNode nodeStart, FloydNode nodeEnd, int weight) {
        line = new QuadCurve();
        label = new Label(String.valueOf(weight));
        arrow = new Polygon();
        numOut = nodeStart.getNumber();
        numIn = nodeEnd.getNumber();
        nodeS = nodeStart;
        nodeE = nodeEnd;

        setPanePosition(nodeStart, nodeEnd);
        setLineSettings(nodeStart, nodeEnd);
        setTextSettings();
        setArrowPosition(nodeStart, nodeEnd, 0);
        setViewOrder(3);

        getChildren().addAll(line, label, arrow);
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
        double centerSX = nodeStart.getCenterX() - getLayoutX();
        double centerSY = nodeStart.getCenterY() - getLayoutY();
        double centerEX = nodeEnd.getCenterX() - getLayoutX();
        double centerEY = nodeEnd.getCenterY() - getLayoutY();

        // Определяем контрольную точку для квадратичной кривой Безье
        Geometry g = new Geometry();
        Point2D control = g.getMiddleCoordinateForLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), height);
        double controlX = control.getX(); // Смещение для изгиба
        double controlY = control.getY();

        // Вычисляем касательную в конце кривой
        double t = 0.9; // Параметр t для вычисления касательной ближе к концу
        double dx = (1 - t) * (controlX - centerSX) + t * (centerEX - controlX);
        double dy = (1 - t) * (controlY - centerSY) + t * (centerEY - controlY);
        double angle = Math.atan2(dy, dx);

        Double[] points = new Double[]{
                0.0, 0.0,
                -arrowHeadSize, -arrowHeadSize / 2,
                -arrowHeadSize, arrowHeadSize / 2
        };

        arrow.getPoints().clear();
        arrow.getPoints().addAll(points);

        // Устанавливаем позицию стрелки в конец кривой, с учетом радиуса узла
        arrow.setTranslateX(centerEX - getPrefWidth() / 2 - (nodeEnd.getRadius() + 3) * Math.cos(angle));
        arrow.setTranslateY(centerEY - getPrefHeight() / 2 - (nodeEnd.getRadius() + 3) * Math.sin(angle));

        arrow.setRotate(Math.toDegrees(angle));

//        double arrowHeadSize = 10;
//        double centerSX = nodeStart.getCenterX() - getLayoutX();
//        double centerSY = nodeStart.getCenterY() - getLayoutY();
//        double centerEX = nodeEnd.getCenterX() - getLayoutX();
//        double centerEY = nodeEnd.getCenterY() - getLayoutY();
//
//        double angle = Math.atan2(centerEY - centerSY, centerEX - centerSX);
//
//        Double[] points = new Double[] {
//                0.0, 0.0,
//                -arrowHeadSize, -arrowHeadSize/2,
//                -arrowHeadSize, arrowHeadSize/2
//        };
//
//        arrow.getPoints().clear();
//        arrow.getPoints().addAll(points);
//
//        arrow.setTranslateX(centerEX - getPrefWidth() / 2 - (nodeEnd.getRadius() + 5) * Math.cos(angle));
//        arrow.setTranslateY(centerEY - getPrefHeight() / 2 - (nodeEnd.getRadius() + 5) * Math.sin(angle));
//
//        arrow.setRotate(Math.toDegrees(angle));

    }

    public void changeCurvature(int rad){
        Geometry g = new Geometry();
        Point2D coords = g.getMiddleCoordinateForLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), 100);
        int coefficient = 1;

        if (Math.abs(line.getStartX() - line.getEndX()) < (rad / 3.0)
                || Math.abs(line.getStartY() - line.getEndY()) < (rad / 3.0)){

            setTranslateX((getPrefWidth()  / 2 - coords.getX()) / -4);             // костыль
            setTranslateY((getPrefHeight() / 2 - coords.getY()) / -4);
            coefficient = 2;
        }

        moveLabel(coords, coefficient);
        setArrowPosition(nodeS, nodeE, 100);
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

    public boolean isHighlighted() {
        return isHighlighted;
    }
}
