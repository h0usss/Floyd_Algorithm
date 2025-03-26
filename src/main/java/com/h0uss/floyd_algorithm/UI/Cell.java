package com.h0uss.floyd_algorithm.UI;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Cell extends TextField {
//    private String color;

    public Cell() {
        super();

        this.setEditable(false);
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getStyleClass().add("cell");
    }

//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getColor() {
//        return color;
//    }

    public void colorIt(String color){
        this.setStyle("-fx-background-color: " + color + ";");
    }

    public void changeFontSize(int fontSize){
        this.setStyle("-fx-font-size: " + fontSize + ";");
    }
}
