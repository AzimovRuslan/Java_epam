import java.util.concurrent.atomic.AtomicInteger;

public class Runner {
    private AtomicInteger c = new AtomicInteger(0);

    private void decrement() {
        c.decrementAndGet();
    }
}
