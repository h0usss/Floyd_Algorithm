package com.h0uss.floyd_algorithm.logic;

public class Algorithm {

    public static FloydMatrix matrix(int[][] inp){
        if (inp == null)
            return null;

        int[][] weight = inp.clone();
        int[][] ordinal = ordinalMatrix(inp.length);

        for (int c = 0; c < weight.length; c++)
            for (int i = 0; i < weight.length; i++)
                for (int j = 0; j < weight[i].length; j++) {

                    if (i == c)
                        break;

                    if (j == c
                            || i == j
                            || weight[i][c] == -1
                            || weight[c][j] == -1)
                        continue;

                    if (weight[i][j] == -1
                            || weight[i][c] + weight[c][j] < weight[i][j]){
                        weight[i][j] = weight[i][c] + weight[c][j];
                        ordinal[i][j] = c;
                    }
                }

        return new FloydMatrix(weight, ordinal);
    }

    private static int[][] ordinalMatrix(int size){
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
