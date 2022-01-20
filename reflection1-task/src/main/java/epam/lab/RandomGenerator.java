package epam.lab;
import annotations.Service;

@Service(id = "RandomGenerator", primary = true)
public class RandomGenerator {
    public static int generation() {
        int startNumber = 0;
        int finishNumber = 100;

        return (startNumber + (int) (Math.random() * finishNumber));
    }
}
