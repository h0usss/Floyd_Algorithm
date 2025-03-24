package com.h0uss.floyd_algorithm;

public class FloydMatrix {
    private double[][] weightMatrix;
    private int[][] ordinalMatrix;

    public FloydMatrix(double[][] weightMatrix, int[][] ordinalMatrix) {
        this.weightMatrix = weightMatrix;
        this.ordinalMatrix = ordinalMatrix;
    }

    public int[][] getOrdinalMatrix() {
        return ordinalMatrix;
    }

    public double[][] getWeightMatrix() {
        return weightMatrix;
    }

    protected void setWeightMatrix(double[][] weightMatrix) {
        this.weightMatrix = weightMatrix.clone();
    }

    protected void setOrdinalMatrix(int[][] ordinalMatrix) {
        this.ordinalMatrix = ordinalMatrix.clone();
    }
}
