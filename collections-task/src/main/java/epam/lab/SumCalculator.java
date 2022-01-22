package epam.lab;

public class SumCalculator {
    private int firstNumber;
    private int secondNumber;

    public SumCalculator(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int calculation() {
        return firstNumber + secondNumber;
    }
}
