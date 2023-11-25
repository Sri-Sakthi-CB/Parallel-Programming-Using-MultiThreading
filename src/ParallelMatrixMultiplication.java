import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelMatrixMultiplication {

    public static int[][] multiply(int[][] matrixA, int[][] matrixB,int numThreads) {
//        int[][] matrixA = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
//
//        int[][] matrixB = {
//                {9, 8, 7},
//                {6, 5, 4},
//                {3, 2, 1}
//        };

        int[][] resultMatrix = new int[matrixA.length][matrixB[0].length];

        // int numThreads = 2; // Adjust this based on the number of available CPU cores
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                Runnable task = new MatrixMultiplicationTask(matrixA, matrixB, resultMatrix, i, j);
                executor.execute(task);
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the result matrix
        for (int[] row : resultMatrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        return resultMatrix;
    }
}
