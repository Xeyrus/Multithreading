package deadlock;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();

    }

    private static class TrainA implements Runnable{
        private Intersection intersection;
        private Random random = new Random();

        public TrainA(Intersection intersection){
            this.intersection = intersection;
        }


        public void run(){
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                }

                intersection.takeRoadA();
            }
        }
    }

    private static class TrainB implements Runnable{
        private Intersection intersection;
        private Random random = new Random();

        public TrainB(Intersection intersection){
            this.intersection = intersection;
        }


        public void run(){
            while(true){
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                }

                intersection.takeRoadB();
            }
        }
    }
}
