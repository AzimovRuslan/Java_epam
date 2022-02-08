package example.realization.lock;

import example.EventChannel;
import example.realization.synchronize.Subscriber;

import java.util.concurrent.locks.ReentrantLock;

public class LSubscriber extends Subscriber {
    private ReentrantLock locker;

    public LSubscriber(String name, String surname, EventChannel eventChannel, ReentrantLock locker) {
        super(name, surname, eventChannel);
        this.locker = locker;
    }

    public LSubscriber(EventChannel eventChannel, ReentrantLock locker) {
        super(eventChannel);
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            getEventChannel().getNewsAboutEvent();
        } finally {
            locker.unlock();
        }
    }
}
