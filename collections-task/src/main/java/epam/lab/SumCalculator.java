package epam.lab;
import java.util.*;

public class SumCalculator {
    private int firstNumber;
    private int secondNumber;
    private int result;
    private int counter = 1;

    public int getResult() {
        return result;
    }

    public SumCalculator(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        result = calculation();
    }

    public int getCounter() {
        return counter;
    }

    public void increaseCounter() {
        this.counter++;
    }

    public int calculation() {
        return firstNumber + secondNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }
}
