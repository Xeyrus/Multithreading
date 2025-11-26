package advanced_locking.rentrant_lock;

import java.util.Random;

public class PriceUpdater extends Thread {
    private PricesContainer pricesContainer;
    private Random random = new Random();

    public PriceUpdater(PricesContainer pricesContainer) {
        this.pricesContainer = pricesContainer;
    }

    @Override
    public void run() {
        while (true) {
            pricesContainer.getLockObject().lock();
            try {
                Thread.sleep(1000);

                pricesContainer.setBitcoinPrice(random.nextDouble() * 20000);
                pricesContainer.setEthereumPrice(random.nextDouble() * 2000);
                pricesContainer.setLitecoinPrice(random.nextDouble() * 500);
                pricesContainer.setBitcoinCashPrice(random.nextDouble() * 5000);
                pricesContainer.setRipplePrice(random.nextDouble() * 500);

                System.out.println("Prices updated by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                pricesContainer.getLockObject().unlock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
