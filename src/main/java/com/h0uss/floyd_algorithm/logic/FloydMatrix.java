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

    public int[][] getOrdinalMatrixIncrement() {
        int[][] res = ordinalMatrix.clone();

        for (int i = 0; i < ordinalMatrix.length; i++)
            for (int j = 0; j < ordinalMatrix[0].length; j++)
                if (i != j)
                    res[i][j] += 1;

        return res;
    }

    public int[][] getWeightMatrix() {
        return weightMatrix;
    }

    protected void setWeightMatrix(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix.clone();
    }

    protected void setOrdinalMatrix(int[][] ordinalMatrix) {
        this.ordinalMatrix = ordinalMatrix.clone();
    }
}
