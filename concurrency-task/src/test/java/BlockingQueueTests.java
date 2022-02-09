import epam.lab.EventChannel;
import epam.lab.events.SomeEvent;
import epam.lab.realization.queue.BQPublisher;
import epam.lab.realization.queue.BQSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.EventsGenerator;
import utils.RandomGenerator;
import utils.SubscribersGenerator;

import java.util.List;

public class BlockingQueueTests {
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

        startThreadsWithBQ(pubCount);
    }

    @Test
    public void lockTest2() {
        int count = 1;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, count);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithBQ(count);
    }

    @Test
    public void lockTest3() {
        subCount = 1;
        pubCount = 3;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithBQ(pubCount);
    }

    @Test
    public void lockTest4() {
        subCount = 3;
        pubCount = 1;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel, subCount);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);

        List<SomeEvent> events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        startThreadsWithBQ(pubCount);
    }

    private static void startThreadsWithBQ(int pubCount) {
        for (int i = 0; i < pubCount; i++) {
            BQPublisher publisher = new BQPublisher(RandomGenerator.eventGeneration(), eventChannel);
            Thread publisherThread = new Thread(publisher);

            publisherThread.start();
            try {
                publisherThread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        new Thread(new BQSubscriber(eventChannel)).start();
    }
}
