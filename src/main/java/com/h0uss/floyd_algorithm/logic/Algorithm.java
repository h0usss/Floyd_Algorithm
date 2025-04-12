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

    public static ArrayList<Integer> getShortestPath(int start, int end, FloydMatrix matrix){

        ArrayList<Integer> res = new ArrayList<>();

        if (matrix == null || start <= 0 || end <= 0 || start == end)
            return res;

        int[][] ordinalMatrix = matrix.getOrdinalMatrix();

        if (start > ordinalMatrix.length || end > ordinalMatrix.length)
            return res;

        res.add(start);
        res.add(end);
        int c = 0;

        while (c != res.size() - 1){
            c = 0;
            for (int i = 0; i < res.size() - 1; i++){
                int inOrd = ordinalMatrix[res.get(i) - 1][res.get(i+1) - 1];

                if (inOrd == -1)
                    return new ArrayList<>();
                else if (inOrd == res.get(i+1))
                    c++;
                else{
                    res.add(i+1, ordinalMatrix[res.get(i) - 1][res.get(i+1) - 1]);
                    break;
                }
            }
        }

        return res;
    }

    public static int getWeightShortestPath(ArrayList<Integer> path, FloydMatrix matrix) {

        if (path.isEmpty())
            return 0;

        int[][] weightMatrix = matrix.getWeightMatrix();
        int res = 0;
        
        for (int i = 0; i < path.size() - 1; i++)
            res += weightMatrix[path.get(i) - 1][path.get(i + 1) - 1];

        return res;
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
