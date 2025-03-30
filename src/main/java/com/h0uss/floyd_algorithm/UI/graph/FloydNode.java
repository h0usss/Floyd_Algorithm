package com.h0uss.floyd_algorithm.UI.graph;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FloydNode extends StackPane {

    private int num;
    private Label label;
    private Circle circle;

    public FloydNode(double radius, int number) {
        super();
        num = number;
        label = new Label(String.valueOf(num));
        circle = new Circle(radius);

        setUpLabel();
        setUpCircle();

        getChildren().addAll(circle, label);
    }


    private void setUpCircle() {
        circle.getStyleClass().add("floyd-node");;
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
