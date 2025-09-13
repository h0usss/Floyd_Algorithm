package com.h0uss.floyd_algorithm.UI.graph;

import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class FloydArrayNodes extends ArrayList<FloydNode> {

    private final Pane pane;
    private final int radius;
    private final Matrix matrix;

    public FloydArrayNodes(Pane parPane, Matrix adjacencyMatrix, int nodeRadius) {
        pane = parPane;
        radius = nodeRadius;
        matrix = adjacencyMatrix;

        for (int i = 0; i < matrix.getSize(); i++)
            add(new FloydNode(radius , i + 1));

        drawNodesToStartPosition();

        pane.getChildren().addAll(this);
    }

    public void drawNodesToStartPosition(){
        double centerX = pane.getPrefWidth() / 2;
        double centerY = pane.getPrefHeight() / 2;
        double bigRadius = Math.min(centerX, centerY) - 20 - radius;
        double grad = Math.toRadians(360.0 / size());

        for (int i = 0; i < size(); i++)
            get(i).setCenter(centerX + Math.cos(grad * i - Math.PI / 2) * bigRadius,
                             centerY + Math.sin(grad * i - Math.PI / 2) * bigRadius);
    }

    public void setNormalColor(){
        for (FloydNode node : this)
            node.setStyleStandard();
    }

    public void setHighlightColor(List<Integer> path){
        for (FloydNode node : this)
            if (path.contains(node.getNumber()))
                node.setStyleHighlight();
    }

    public void dragNodeAndLine(FloydArrayLines lines, Pane pane) {
        for (FloydNode node : this){
            node.setOnMouseDragged(null);

            node.setOnMouseDragged(event -> {
                double newX = event.getSceneX() - node.getBoundsInParent().getWidth()/2;
                double newY = event.getSceneY() - node.getBoundsInParent().getHeight()/2;

                newX = Math.max(0, Math.min(newX, pane.getWidth() - node.getBoundsInParent().getWidth()));
                newY = Math.max(0, Math.min(newY, pane.getHeight() - node.getBoundsInParent().getHeight()));

                node.setLayoutX(newX);
                node.setLayoutY(newY);

                lines.update(matrix, this);
            });
        }
    }

    public void addNewNodeByNum(int number){
        add(new FloydNode(radius , number));
    }
}
