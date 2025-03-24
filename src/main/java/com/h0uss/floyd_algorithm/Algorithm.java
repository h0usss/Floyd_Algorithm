package com.h0uss.floyd_algorithm;

public class Algorithm {
    static double inf = Double.POSITIVE_INFINITY;

    public static FloydMatrix matrix(double[][] inp){
        if (inp == null)
            return null;

        double[][] weight = inp.clone();
        int[][] ordinal = ordinalMatrix(inp.length);

        for (int c = 0; c < weight.length; c++)
            for (int i = 0; i < weight.length; i++)
                for (int j = 0; j < weight[i].length; j++) {

                    if (weight[c][j] == inf
                            || weight[i][c] == inf
                            || weight[i][j] == -1
                            || j == c)
                        continue;

                    if (i == c)
                        break;

                    if (weight[i][c] + weight[c][j] < weight[i][j]){
                        weight[i][j] = weight[i][c] + weight[c][j];
                        ordinal[i][j] = c;
                    }
                }

        return new FloydMatrix(weight, ordinal);
    }

    public static int[][] ordinalMatrix(int size){
        int[][] out = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                if (i == j)
                    out[i][j] = -1;
                else
                    out[i][j] = j;
            }

        return out;
    }
}
