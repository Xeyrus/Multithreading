package inter_thread_communication.backpressure;

import java.io.FileWriter;
import java.io.IOException;

public class MatricesMultiplierConsumer extends Thread {
    private ThreadSafeQueue queue;
    private FileWriter fileWriter;
    private int N;

    public MatricesMultiplierConsumer(FileWriter fileWriter, ThreadSafeQueue queue, int N) {
        this.fileWriter = fileWriter;
        this.queue = queue;
        this.N = N;
    }

    public void run() {
        while (true) {
            MatricesPair matricesPair = queue.remove();
            if (matricesPair == null) {
                System.out.println("No more matrices to process. Consumer thread is terminating");
                break;
            }

            float[][] result = multiplyMatrices(matricesPair.matrix1, matricesPair.matrix2);

            try {
                saveMatrixToFile(fileWriter, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveMatrixToFile(FileWriter fileWriter, float[][] matrix) throws IOException {
        for (int r = 0; r < N; r++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int c = 0; c < N; c++) {
                stringBuilder.append(String.format("%.2f", matrix[r][c]));
                if (c < N - 1) {
                    stringBuilder.append(", ");
                }
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.write('\n');
        }
        fileWriter.write('\n');
    }

    private float[][] multiplyMatrices(float[][] matrix1, float[][] matrix2) {
        float result[][] = new float[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int k = 0; k < N; k++) {
                    result[r][c] += matrix1[r][k] * matrix2[k][c];
                }
            }
        }
        return result;
    }

}
