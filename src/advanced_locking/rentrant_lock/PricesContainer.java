package advanced_locking.rentrant_lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class PricesContainer {
    private Lock lockObject = new ReentrantLock();

    private double bitcoinPrice;
    private double ethereumPrice;
    private double litecoinPrice;
    private double bitcoinCashPrice;
    private double ripplePrice;

    public Lock getLockObject() {
        return lockObject;
    }

    public double getBitcoinPrice() {
        return bitcoinPrice;
    }

    public void setBitcoinPrice(double bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }

    public double getEthereumPrice() {
        return ethereumPrice;
    }

    public void setEthereumPrice(double ethereumPrice) {
        this.ethereumPrice = ethereumPrice;
    }

    public double getLitecoinPrice() {
        return litecoinPrice;
    }

    public void setLitecoinPrice(double litecoinPrice) {
        this.litecoinPrice = litecoinPrice;
    }

    public double getBitcoinCashPrice() {
        return bitcoinCashPrice;
    }

    public void setBitcoinCashPrice(double bitcoinCashPrice) {
        this.bitcoinCashPrice = bitcoinCashPrice;
    }

    public double getRipplePrice() {
        return ripplePrice;
    }

    public void setRipplePrice(double ripplePrice) {
        this.ripplePrice = ripplePrice;
    }
}
