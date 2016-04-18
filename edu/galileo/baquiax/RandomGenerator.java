package edu.galileo.baquiax;
import java.util.Random;

public class RandomGenerator {
    private Random r;
    public RandomGenerator() {
        this.r = new Random();
    }
    
    public int getRandomNumber(int min, int max) {
        if (this.r == null) this.r = new Random();
        return (int)(r.nextDouble() * max + min);
    }
}