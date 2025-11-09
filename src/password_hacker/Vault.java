package password_hacker;

public class Vault {
    private int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean isCorrectpassword(int guess) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.password == guess;
    }

//    public int password() {
//        return this.password;
//    }
}
