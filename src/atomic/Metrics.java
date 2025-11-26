package atomic;

public class Metrics {
    private long count = 0;
    private volatile double average = 0.0;

    // Getters and setter for int, byte, char, long and objects are atomic in Java
    // Have to check for double and floats

    public synchronized void addSample(long sample) {
        double currentSum = average * count;
        count++;
        average = (currentSum + sample) / count;
    }

    public double getAverage() {
        return average;
    }
}
