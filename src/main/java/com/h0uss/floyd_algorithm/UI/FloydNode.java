package com.h0uss.floyd_algorithm.UI;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

public class FloydNode extends StackPane {

    private Label label;
    private Circle circle;

    public FloydNode(double radius, String text) {
        super();
        label = new Label(text);
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
}
