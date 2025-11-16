package deadlock;

import java.util.Random;

public class MainUpdated {

    public static void main(String[] args) {

        IntersectionUpdated intersection = new IntersectionUpdated();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();

    }

    private static class TrainA implements Runnable{
        private IntersectionUpdated intersection;
        private Random random = new Random();

        public TrainA(IntersectionUpdated intersection){
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
        private IntersectionUpdated intersection;
        private Random random = new Random();

        public TrainB(IntersectionUpdated intersection){
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
