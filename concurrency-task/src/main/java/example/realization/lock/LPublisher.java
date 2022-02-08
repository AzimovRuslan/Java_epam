package example.realization.lock;

import example.EventChannel;
import example.events.SomeEvent;
import example.realization.synchronize.Publisher;

import java.util.concurrent.locks.ReentrantLock;

public class LPublisher extends Publisher {
    private static ReentrantLock locker;

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
