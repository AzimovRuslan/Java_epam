import epam.lab.SumCalculator;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    public void calculationTest1() {
        SumCalculator calculator = new SumCalculator(10, 15);
        assertEquals(25, calculator.calculation());
    }

    @Test
    public void calculationTest2() {
        SumCalculator calculator = new SumCalculator(-10, -3);
        assertEquals(-13, calculator.calculation());
    }

    @Test
    public void calculationTest3() {
        SumCalculator calculator = new SumCalculator(-20, 110);
        assertEquals(90, calculator.calculation());
    }
}
