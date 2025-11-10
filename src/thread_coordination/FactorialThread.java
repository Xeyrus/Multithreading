package thread_coordination;

import java.math.BigInteger;

public class FactorialThread extends Thread{

    private boolean isFinished = false;
    private long inputNumber;
    private BigInteger result;

    public FactorialThread(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
        this.result = factorial(inputNumber);
        this.isFinished = true;
    }

    public BigInteger factorial(long n){
        BigInteger tempResult = BigInteger.ONE;

        for (long i = 2; i<=n ; i++){
            if(!this.isInterrupted())
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            else
                return BigInteger.ZERO;
        }

        return tempResult;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public BigInteger getResult() {
        return result;
    }
}
