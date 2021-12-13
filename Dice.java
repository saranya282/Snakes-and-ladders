import java.util.Random;
public class Dice {
    public int roll() {
        Random rand = new Random();
        return rand.nextInt(1, 7);
    }
    
    }
