package epam.lab;
import java.util.*;

public class SumCalculator implements Comparator {
    private int firstNumber;
    private int secondNumber;
    private Date date = new Date();
    private int result;

    public Date getDate() {
        return date;
    }

    public int getResult() {
        return result;
    }

    public SumCalculator(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public void calculation() {
        result = firstNumber + secondNumber;
    }


    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }
}
