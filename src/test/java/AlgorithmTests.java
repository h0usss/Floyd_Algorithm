import com.h0uss.floyd_algorithm.Algorithm;
import com.h0uss.floyd_algorithm.FloydMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AlgorithmTests {
    double inf = Double.POSITIVE_INFINITY;

    @Test
    public void testAlgorithmMatrix() {

        double[][] inp = new double[][] {
                new double[]{ -1, 5, 3, inf, inf, inf, inf },
                new double[]{ 5, -1, 1, 5, 2, inf, inf},
                new double[]{ 3, 1, -1, 7, inf, inf, 12 },
                new double[]{ inf, 5,  7, -1, 3, inf, 3},
                new double[]{ inf, 2, inf, 3 ,-1, 1, inf},
                new double[]{ inf, inf, inf, 1, 1, -1, inf},
                new double[]{ inf, inf, 12, 3, inf, 4, -1},
        };

        double[][] outW = new double[][] {
                new double[]{ -1, 4, 3, 8, 6, 7, 11 },
                new double[]{ 4, -1, 1, 4, 2, 3, 7},
                new double[]{ 3, 1, -1, 5, 3, 4, 8 },
                new double[]{ 9, 5,  6, -1, 3, 4, 3},
                new double[]{ 6, 2, 3, 2, -1, 1, 5},
                new double[]{ 7, 3, 4, 1, 1, -1, 4},
                new double[]{ 11, 7, 8, 3, 5, 4, -1}
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
