package com.h0uss.floyd_algorithm.logic;

import java.util.ArrayList;

public class Algorithm {

    public static FloydMatrix mainAlgorithm(int[][] inp){
        if (inp == null)
            return null;

        int[][] weight = inp.clone();
        int[][] ordinal = ordinalMatrix(inp.length, weight);

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
                        ordinal[i][j] = c + 1;
                    }
                }

        return new FloydMatrix(weight, ordinal);
    }

    public static ArrayList<Integer> findShortestPath(int start, int end, FloydMatrix matrix){

        ArrayList<Integer> rez = new ArrayList<>();

        if (matrix == null || start <= 0 || end <= 0 || start == end)
            return rez;

        int[][] ordinalMatrix = matrix.getOrdinalMatrix();
        int[][] weightMatrix = matrix.getWeightMatrix();

        if (start > ordinalMatrix.length || end > ordinalMatrix.length)
            return rez;

        rez.add(start);
        rez.add(end);
        int c = 0;

        while (c != rez.size() - 1){
            c = 0;
            for (int i = 0; i < rez.size() - 1; i++){
                int inOrd = ordinalMatrix[rez.get(i) - 1][rez.get(i+1) - 1];

                if (inOrd == -1)
                    return new ArrayList<>();
                else if (inOrd == rez.get(i+1))
                    c++;
                else{
                    rez.add(i+1, ordinalMatrix[rez.get(i) - 1][rez.get(i+1) - 1]);
                    break;
                }
            }
        }

        int count = 0;

        for (int i = 0; i < rez.size() - 1; i++)
            count += weightMatrix[rez.get(i) - 1][rez.get(i+1) - 1];

        for (int i = 0; i < rez.size(); i++)
            rez.set(i, rez.get(i));

        rez.add(0, count);
        return rez;
    }

    private static int[][] ordinalMatrix(int size, int[][] weight){
        int[][] out = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++){
                if (i == j || weight[i][j] == -1)
                    out[i][j] = -1;
                else
                    out[i][j] = j + 1;
            }

        return out;
    }
}
