package no_backpressure;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String INPUT_FILE = "./Multithreading/src/inter_thread_communication/out/matrices";
    private static final String OUTPUT_FILE = "./Multithreading/src/inter_thread_communication/out/matrices_result.txt";
    private static int N = 10;

    public static void main(String[] args) throws IOException {
        ThreadSafeQueue queue = new ThreadSafeQueue();
        File inputFile = new File(INPUT_FILE);
        File outputFile = new File(OUTPUT_FILE);

        MatricesReaderProducer matricesReader = new MatricesReaderProducer(new FileReader(inputFile), queue, N);
        MatricesMultiplierConsumer matricesConsumer = new MatricesMultiplierConsumer(new FileWriter(outputFile), queue,
                N);

        matricesConsumer.start();
        matricesReader.start();
    }
}