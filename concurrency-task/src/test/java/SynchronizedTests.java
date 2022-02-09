import epam.lab.EventChannel;
import epam.lab.events.SomeEvent;
import epam.lab.realization.synchronize.Publisher;
import epam.lab.realization.synchronize.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.EventsGenerator;
import utils.RandomGenerator;
import utils.SubscribersGenerator;

import java.util.List;

public class SynchronizedTests {
    static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedTests.class);
    static EventChannel eventChannel = new EventChannel();
    static int subCount = 0;
    static int pubCount = 0;

    @Test
    public void synchronizedTest1() {
        int count = 2;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, count);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startSynchronizationThreads(count);
    }

    @Test
    public void synchronizedTest2() {
        subCount = 2;
        pubCount = 5;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startSynchronizationThreads(pubCount);
    }

    @Test
    public void synchronizedTest3() {
        subCount = 4;
        pubCount = 1;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startSynchronizationThreads(pubCount);
    }

    private static void startSynchronizationThreads(int pubCount) {
        for (int i = 0; i < pubCount; i++) {
            Publisher publisher = new Publisher(RandomGenerator.eventGeneration(), eventChannel);
            Thread publisherThread = new Thread(publisher);

            publisherThread.start();
            try {
                publisherThread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        new Thread(new Subscriber(eventChannel)).start();
    }
}
