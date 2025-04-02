package com.h0uss.floyd_algorithm.UI.graph;

import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class FloydArrayNodes extends ArrayList<FloydNode> {

    private Pane pane;
    private int radius;
    private Matrix matrix;

    public FloydArrayNodes(Pane parPane, Matrix adjacencyMatrix, int nodeRadius) {
        pane = parPane;
        radius = nodeRadius;
        matrix = adjacencyMatrix;

        for (int i = 0; i < matrix.getSize(); i++)
            add(new FloydNode(radius , i + 1));

        drawNodesToStartPosition();
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
}
