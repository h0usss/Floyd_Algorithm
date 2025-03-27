package com.h0uss.floyd_algorithm.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AlgorithmTests {

    @Test
    public void testAlgorithmMatrix() {

        int[][] inp = new int[][] {
                new int[]{ -1, 5, 3, -1, -1, -1, -1 },
                new int[]{ 5, -1, 1, 5, 2, -1, -1},
                new int[]{ 3, 1, -1, 7, -1, -1, 12 },
                new int[]{ -1, 5,  7, -1, 3, -1, 3},
                new int[]{ -1, 2, -1, 3 ,-1, 1, -1},
                new int[]{ -1, -1, -1, 1, 1, -1, -1},
                new int[]{ -1, -1, 12, 3, -1, 4, -1},
        };

        int[][] outW = new int[][] {
                new int[]{ -1, 4, 3, 8, 6, 7, 11 },
                new int[]{ 4, -1, 1, 4, 2, 3, 7},
                new int[]{ 3, 1, -1, 5, 3, 4, 8 },
                new int[]{ 9, 5,  6, -1, 3, 4, 3},
                new int[]{ 6, 2, 3, 2, -1, 1, 5},
                new int[]{ 7, 3, 4, 1, 1, -1, 4},
                new int[]{ 11, 7, 8, 3, 5, 4, -1}
        };

        int[][] outO = new int[][] {
                new int[]{ -1, 2, 2, 5, 2, 4, 5 },
                new int[]{ 2, -1, 2, 5, 4, 4, 5 },
                new int[]{ 0, 1, -1, 5, 1, 4, 5 },
                new int[]{ 2, 1, 1, -1, 4, 4, 6 },
                new int[]{ 2, 1, 1, 5, -1, 5, 5 },
                new int[]{ 4, 4, 4, 3, 4, -1, 3 },
                new int[]{ 5, 5, 5, 3, 5, 5, -1 }
        };

        FloydMatrix ex = new FloydMatrix(outW, outO);
        FloydMatrix re = Algorithm.matrix(inp);
        assertArrayEquals(ex.getWeightMatrix(), re.getWeightMatrix());
        assertArrayEquals(ex.getOrdinalMatrix(), re.getOrdinalMatrix());
    }
}
