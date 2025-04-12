package com.h0uss.floyd_algorithm.logic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AlgorithmTests {

    @Test
    public void testAlgorithmMatrix() {

        int[][] inp = new int[][] {
                new int[]{ -1,  5,  1, -1, -1, -1, -1},
                new int[]{ -1, -1, -1,  7,  1,  6, -1},
                new int[]{ -1,  2, -1,  6,  7, -1, -1},
                new int[]{ -1, -1,  7, -1, -1,  4,  6},
                new int[]{ -1, -1, -1,  3, -1,  5,  9},
                new int[]{ -1,  7, -1, -1, -1, -1,  2},
                new int[]{ -1, -1, -1, -1, -1, -1, -1},
        };

        int[][] outW = new int[][] {
                new int[]{ -1,  3,  1,  7,  4,  9, 11},
                new int[]{ -1, -1, 11,  4,  1,  6,  8},
                new int[]{ -1,  2, -1,  6,  3,  8, 10},
                new int[]{ -1,  9,  7, -1, 10,  4,  6},
                new int[]{ -1, 12, 10,  3, -1,  5,  7},
                new int[]{ -1,  7, 18, 11,  8, -1,  2},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        int[][] outO = new int[][] {
                new int[]{ -1,  3,  3,  3,  3,  3,  6},
                new int[]{ -1, -1,  5,  5,  5,  6,  6},
                new int[]{ -1,  2, -1,  4,  2,  2,  6},
                new int[]{ -1,  3,  3, -1,  3,  6,  7},
                new int[]{ -1,  4,  4,  4, -1,  6,  6},
                new int[]{ -1,  2,  5,  5,  2, -1,  7},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        FloydMatrix ex = new FloydMatrix(outW, outO);
        FloydMatrix re = Algorithm.mainAlgorithm(inp);
        assertArrayEquals(ex.getWeightMatrix(), re.getWeightMatrix());
        assertArrayEquals(ex.getOrdinalMatrix(), re.getOrdinalMatrix());
    }

    @Test
    public void testAlgorithmFindShortestPath() {
        int[][] outW = new int[][] {
                new int[]{ -1,  3,  1,  7,  4,  9, 11},
                new int[]{ -1, -1, 11,  4,  1,  6,  8},
                new int[]{ -1,  2, -1,  6,  3,  8, 10},
                new int[]{ -1,  9,  7, -1, 10,  4,  6},
                new int[]{ -1, 12, 10,  3, -1,  5,  7},
                new int[]{ -1,  7, 18, 11,  8, -1,  2},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        int[][] outO = new int[][] {
                new int[]{ -1,  3,  3,  3,  3,  3,  6},
                new int[]{ -1, -1,  5,  5,  5,  6,  6},
                new int[]{ -1,  2, -1,  4,  2,  2,  6},
                new int[]{ -1,  3,  3, -1,  3,  6,  7},
                new int[]{ -1,  4,  4,  4, -1,  6,  6},
                new int[]{ -1,  2,  5,  5,  2, -1,  7},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        FloydMatrix ex = new FloydMatrix(outW, outO);
        ArrayList<Integer> res = Algorithm.getShortestPath(1, 7, ex);
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(1, 3, 2, 6, 7));
        assertEquals(ans, res);
    }

    @Test
    public void badTestAlgorithmFindShortestPath() {
        int[][] outW = new int[][] {
                new int[]{ -1,  3,  1,  7,  4,  9, 11},
                new int[]{ -1, -1, 11,  4,  1,  6,  8},
                new int[]{ -1,  2, -1,  6,  3,  8, 10},
                new int[]{ -1,  9,  7, -1, 10,  4,  6},
                new int[]{ -1, 12, 10,  3, -1,  5,  7},
                new int[]{ -1,  7, 18, 11,  8, -1,  2},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        int[][] outO = new int[][] {
                new int[]{ -1,  3,  3,  3,  3,  3,  6},
                new int[]{ -1, -1,  5,  5,  5,  6,  6},
                new int[]{ -1,  2, -1,  4,  2,  2,  6},
                new int[]{ -1,  3,  3, -1,  3,  6,  7},
                new int[]{ -1,  4,  4,  4, -1,  6,  6},
                new int[]{ -1,  2,  5,  5,  2, -1,  7},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        FloydMatrix ex = new FloydMatrix(outW, outO);
        ArrayList<Integer> res = Algorithm.getShortestPath(1, 7, ex);
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(1, 3, 2, 6, 7));
        assertEquals(ans, res);
    }

    @Test
    public void testAlgorithmFindShortestPath2() {
        int[][] inp = new int[][] {
                new int[]{ -1,  1, -1, -1, -1},
                new int[]{ -1, -1,  1, -1, -1},
                new int[]{ -1, -1, -1, 12, -1},
                new int[]{ -1,  2, -1, -1,  1},
                new int[]{ -1, -1, -1, -1, -1},
        };

        int[][] outW = new int[][] {
                new int[]{ -1,  1,  2, 14, 15},
                new int[]{ -1, -1,  1, 13, 14},
                new int[]{ -1, 14, -1, 12, 13},
                new int[]{ -1,  2, 3, -1,  1},
                new int[]{ -1, -1, -1, -1, -1},
        };

        int[][] outO = new int[][] {
                new int[]{ -1,  2,  2,  3,  4},
                new int[]{ -1, -1,  3,  3,  4},
                new int[]{ -1,  4, -1,  4,  4},
                new int[]{ -1,  2,  2, -1,  5},
                new int[]{ -1, -1, -1, -1, -1},
        };

        FloydMatrix ex = new FloydMatrix(outW, outO);
        FloydMatrix re = Algorithm.mainAlgorithm(inp);
        assertArrayEquals(ex.getWeightMatrix(), re.getWeightMatrix());
        assertArrayEquals(ex.getOrdinalMatrix(), re.getOrdinalMatrix());
    }

    @Test
    public void getWeightShortestPathTest(){
        int[][] outW = new int[][] {
                new int[]{ -1,  3,  1,  7,  4,  9, 11},
                new int[]{ -1, -1, 11,  4,  1,  6,  8},
                new int[]{ -1,  2, -1,  6,  3,  8, 10},
                new int[]{ -1,  9,  7, -1, 10,  4,  6},
                new int[]{ -1, 12, 10,  3, -1,  5,  7},
                new int[]{ -1,  7, 18, 11,  8, -1,  2},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        int[][] outO = new int[][] {
                new int[]{ -1,  3,  3,  3,  3,  3,  6},
                new int[]{ -1, -1,  5,  5,  5,  6,  6},
                new int[]{ -1,  2, -1,  4,  2,  2,  6},
                new int[]{ -1,  3,  3, -1,  3,  6,  7},
                new int[]{ -1,  4,  4,  4, -1,  6,  6},
                new int[]{ -1,  2,  5,  5,  2, -1,  7},
                new int[]{ -1, -1, -1, -1, -1, -1, -1}
        };

        FloydMatrix ex = new FloydMatrix(outW, outO);
        int res = Algorithm.getWeightShortestPath(Algorithm.getShortestPath(1, 7, ex), ex);
        assertEquals(11, res);
    }
}
