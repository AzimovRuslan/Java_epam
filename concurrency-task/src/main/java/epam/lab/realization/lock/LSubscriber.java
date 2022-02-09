package epam.lab.realization.lock;

import epam.lab.EventChannel;
import epam.lab.realization.synchronize.Subscriber;

import java.util.concurrent.locks.ReentrantLock;

public class LSubscriber extends Subscriber {
    private ReentrantLock locker;

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
