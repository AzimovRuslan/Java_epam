import epam.lab.ApplicationContext;
import epam.lab.Greeter;
import epam.lab.RandomGenerator;
import interfaces.StringCreator;
import org.testng.annotations.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void getBean_id_true1() {
        assertTrue(applicationContext.getBean("RandomGenerator") instanceof RandomGenerator);
    }

    @Test
    public void getBean_id_true2() {
        assertTrue(applicationContext.getBean("Greeter") instanceof Greeter);
    }

    @Test
    public void getBean_id_false() {
        assertFalse(applicationContext.getBean("RandomGenerator") instanceof Greeter);
    }

    @Test
    public void getBean_implementationClass_true1() {
        assertTrue(applicationContext.getBean(RandomGenerator.class) instanceof RandomGenerator);
    }

    @Test
    public void getBean_implementationClass_true2() {
        assertTrue(applicationContext.getBean(Greeter.class) instanceof Greeter);
    }

    @Test
    public void getBean_implementationClass_false() {
        assertFalse(applicationContext.getBean(Greeter.class) instanceof RandomGenerator);
    }

    @Test
    public void getBean_implementationInterface_false() {
        assertFalse(applicationContext.getBean(StringCreator.class) instanceof RandomGenerator);
    }

    @Test
    public void getBean_implementationInterface_true() {
        assertTrue(applicationContext.getBean(StringCreator.class) instanceof Greeter);
    }

    @Test
    public void getBean_idAndImplementationClass_true1() {
        assertTrue(applicationContext.getBean("RandomGenerator", RandomGenerator.class) instanceof RandomGenerator);
    }

    @Test
    public void getBean_idAndImplementationClass_true2() {
        assertTrue(applicationContext.getBean("Greeter", Greeter.class) instanceof Greeter);
    }

    @Test
    public void getBean_idAndImplementationClass_false() {
        assertFalse(applicationContext.getBean("Greeter", Greeter.class) instanceof RandomGenerator);
    }

    @Test
    public void getBean_idAndImplementationInterface_true1() {
        assertTrue(applicationContext.getBean("Greeter", StringCreator.class) instanceof Greeter);
    }

    @Test
    public void getBean_idAndImplementationInterface_false() {
        assertFalse(applicationContext.getBean("Greeter", StringCreator.class) instanceof RandomGenerator);
    }

    @Test
    public void buildTest() {
        applicationContext.registerBean(Greeter.class);
        applicationContext.registerBean(RandomGenerator.class);
        Deque<Object> beans = applicationContext.build();
        assertTrue(beans.getFirst() instanceof RandomGenerator);
        assertTrue(beans.getLast() instanceof Greeter);
    }
}
