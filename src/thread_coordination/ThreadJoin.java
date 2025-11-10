package thread_coordination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        List<Long> inputNumbers = Arrays.asList(1L, 3435L, 35435L, 2332L, 4656L, 23L, 5556L, 1000000L);
        List<FactorialThread> threads = new ArrayList<>();

        for (Long input : inputNumbers) {
            threads.add(new FactorialThread(input));
        }

        for (FactorialThread thread : threads) {
//            thread.setDaemon(true);
            thread.start();
        }

        for (FactorialThread thread : threads) {
            thread.join(2000);          // Main method waits for 2 sec for all the thread sto complete
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("Calculation for number " + inputNumbers.get(i) + " still in progress");
                factorialThread.interrupt();
                System.out.println("Thread "+ factorialThread.getName() +" stopped prematurely for number : "+ inputNumbers.get(i));
            }
        }


    }
}
