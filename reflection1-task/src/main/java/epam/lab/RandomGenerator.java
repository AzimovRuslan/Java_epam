package epam.lab;
import annotations.Service;

@Service(id = Constants.RANDOM_GENERATOR, primary = true)
public class RandomGenerator {
    private final int randomNumber = generation();

    public int getRandomNumber() {
        return randomNumber;
    }

    public int generation() {
        int startNumber = 0;
        int finishNumber = 100;

        return (startNumber + (int) (Math.random() * finishNumber));
    }
}
