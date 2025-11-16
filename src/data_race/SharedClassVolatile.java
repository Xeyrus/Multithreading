package data_race;

public class SharedClassVolatile {

    public static void main(String[] args) {
        DataRaceClass dataRaceClass = new DataRaceClass();
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                dataRaceClass.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                dataRaceClass.checkForDataRace();
            }
        });

        thread1.start();
        thread2.start();
    }

    public static class DataRaceClass {
        private volatile int x = 0;
        private volatile int y = 0;

        // Adding volatile is equivalent to memory fence or a memory barrier.
        // So instructions before volatile is executed before and instructions
        // after volatile will be executed after. Thus no data race.

        // Adding synchronized also helps but has a performance penalty.
        public void increment(){
            x++;
            y++;
        }

        public void checkForDataRace(){
            if(y>x){
                System.out.println("y > x - Data race is detected");
            }
        }
    }

}
