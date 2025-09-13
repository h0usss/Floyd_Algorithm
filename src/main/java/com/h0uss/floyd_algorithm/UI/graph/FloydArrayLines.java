package com.h0uss.floyd_algorithm.UI.graph;

import com.h0uss.floyd_algorithm.UI.matrix.Matrix;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FloydArrayLines extends ArrayList<FloydLine> {

    private final Pane pane;

    public FloydArrayLines(Pane pane) {
        this.pane = pane;
    }

    public void setNormalColor(){
        for (FloydLine line : this)
            line.setStyleStandard();
    }

    public void setHighlightColor(List<Integer> path){
        for (int i = 0; i < path.size() - 1; i++)
            Objects.requireNonNull(getLineByOutAndIn(path.get(i), path.get(i + 1))).setStyleHighlight();
    }

    private FloydLine getLineByOutAndIn(Integer out, Integer in){
        for (FloydLine line : this){
            if ((line.getNumOut() == out 
                && line.getNumIn() == in)
                || 
                (line.isNoArrow()
                && line.getNumOut() == in 
                && line.getNumIn() == out))
                return line;
        }
            
        return null;
    }

    public void update(Matrix adjacencyMatrix, FloydArrayNodes nodes) {

        List<Pair<Integer, Integer>> highlightedLines = new ArrayList<>();
        for (FloydLine line : this)
            if (line.isHighlighted())
                highlightedLines.add(new Pair<>(line.getNumOut(), line.getNumIn()));
                
        int[][] matrix = adjacencyMatrix.getMatrix();
        pane.getChildren().removeAll(this);
        clear();

        for (int i = 0; i < adjacencyMatrix.getSize(); i++) {
            for (int j = i + 1; j < adjacencyMatrix.getSize(); j++) {
                int weightIJ = matrix[i][j];
                int weightJI = matrix[j][i];

                if (weightIJ == -1 && weightJI == -1) continue;

                FloydLine line;
                if (weightJI == -1) {
                    line = new FloydLine(nodes.get(i), nodes.get(j), weightIJ);
                } else if (weightIJ == -1) {
                    line = new FloydLine(nodes.get(j), nodes.get(i), weightJI);
                } else if (weightIJ == weightJI) {
                    line = new FloydLine(nodes.get(i), nodes.get(j), weightIJ);
                    line.setNoArrow();
                } else {
                    line = new FloydLine(nodes.get(i), nodes.get(j), matrix[i][j]);
                    line.changeCurvature();
                    FloydLine line2 = new FloydLine(nodes.get(j), nodes.get(i), matrix[j][i]);
                    line2.changeCurvature();
                    this.add(line2);
                }

                if (highlightedLines.contains(new Pair<>(line.getNumOut(), line.getNumIn()))) {
                    line.setStyleHighlight();
                }
                this.add(line);
            }
            
        }
        pane.getChildren().removeAll(this);
        pane.getChildren().addAll(this);
    }

}
