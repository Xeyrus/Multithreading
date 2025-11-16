package data_race;

public class SharedClass{

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
        private int x = 0;
        private int y = 0;

        // y>x shouldn't occur ideally, but since compiler does auto ordering to
        // optimize code. So data race can occur.
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
