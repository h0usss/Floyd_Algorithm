package com.h0uss.floyd_algorithm.logic;

public class FloydMatrix {
    private int[][] weightMatrix;
    private int[][] ordinalMatrix;

    public FloydMatrix(int[][] weightMatrix, int[][] ordinalMatrix) {
        this.weightMatrix = weightMatrix;
        this.ordinalMatrix = ordinalMatrix;
    }

    public int[][] getOrdinalMatrix() {
        return ordinalMatrix;
    }

    public int[][] getWeightMatrix() {
        return weightMatrix;
    }
}
