package epam.lab;

public class SumCalculator {
    private final int firstNumber;
    private final int secondNumber;
    private final int result;

    public int getResult() {
        return result;
    }

    public SumCalculator(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        result = calculation();
    }

    public int calculation() {
        return firstNumber + secondNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(result);
    }
}
