package com.h0uss.floyd_algorithm.UI.graph;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class FloydNode extends StackPane {

    private final int num;
    private final Label label;
    private final Circle circle;

    public FloydNode(double radius, int number) {
        super();
        num = number;
        circle = new Circle(radius);
        label   = new Label(String.valueOf(num));

        setUpLabel();
        setStyleStandard();
        setViewOrder(2);

        getChildren().addAll(circle, label);
    }


    public void setStyleStandard() {
        circle.getStyleClass().removeAll("floyd-node-highlight");
        circle.getStyleClass().add("floyd-node");
    }

    public void setStyleHighlight() {
        circle.getStyleClass().removeAll("floyd-node");
        circle.getStyleClass().add("floyd-node-highlight");
    }

    private void setUpLabel() {
        label.setTextFill(Color.BLACK);
    }

    public void setCenter(double x, double y) {
        setLayoutX(x - circle.getRadius());
        setLayoutY(y - circle.getRadius());
    }


    public double getCenterX() {
        return getLayoutX() + circle.getRadius();
    }

    public double getCenterY() {
        return getLayoutY() + circle.getRadius();
    }

    public double getRadius() {
        return circle.getRadius();
    }

    public int getNumber() {
        return num;
    }
}
