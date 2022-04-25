import epam.lab.EventChannel;
import epam.lab.events.SomeEvent;
import epam.lab.realization.lock.LPublisher;
import epam.lab.realization.lock.LSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.EventsGenerator;
import utils.RandomGenerator;
import utils.SubscribersGenerator;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LockTests {
    static final Logger LOGGER = LoggerFactory.getLogger(LockTests.class);
    static EventChannel eventChannel = new EventChannel();
    static int subCount = 0;
    static int pubCount = 0;

    @Test
    public void lockTest1() {
        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithLocks(pubCount);
    }

    @Test
    public void lockTest2() {
        subCount = 2;
        pubCount = 5;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithLocks(pubCount);
    }

    @Test
    public void lockTest3() {
        subCount = 4;
        pubCount = 1;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithLocks(pubCount);
    }

    @Test
    public void lockTest4() {
        int count = 6;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, count);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithLocks(count);
    }

    private static void startThreadsWithLocks(int pubCount) {
        ReentrantLock reentrantLock = new ReentrantLock();

        for (int i = 0; i < pubCount; i++) {
            LPublisher publisher = new LPublisher(RandomGenerator.eventGeneration(), eventChannel, reentrantLock);
            Thread publisherThread = new Thread(publisher);

            publisherThread.start();
            try {
                publisherThread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        new Thread(new LSubscriber(eventChannel, reentrantLock)).start();
    }
}
