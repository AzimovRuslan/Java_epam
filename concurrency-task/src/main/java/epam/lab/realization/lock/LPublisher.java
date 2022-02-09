package epam.lab.realization.lock;

import epam.lab.realization.synchronize.Publisher;
import epam.lab.EventChannel;
import epam.lab.events.SomeEvent;

import java.util.concurrent.locks.ReentrantLock;

public class LPublisher extends Publisher {
    private final ReentrantLock locker;

    public LPublisher(SomeEvent event, EventChannel eventChannel, ReentrantLock locker) {
        super(event, eventChannel);
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            sendNews();
        } finally {
            locker.unlock();
        }
    }
}
