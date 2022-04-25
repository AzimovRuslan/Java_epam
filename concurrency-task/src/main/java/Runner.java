import utils.Constants;
import epam.lab.EventChannel;
import epam.lab.events.SomeEvent;
import epam.lab.realization.lock.LPublisher;
import epam.lab.realization.lock.LSubscriber;
import epam.lab.realization.queue.BQPublisher;
import epam.lab.realization.queue.BQSubscriber;
import epam.lab.realization.synchronize.Publisher;
import epam.lab.realization.synchronize.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EventsGenerator;
import utils.PropertiesReader;
import utils.RandomGenerator;
import utils.SubscribersGenerator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private static final PropertiesReader PROPERTIES_READER = new PropertiesReader();
    private static final EventChannel EVENT_CHANNEL = new EventChannel();
    private static List<SomeEvent> events;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            SubscribersGenerator subscribersGenerator = new SubscribersGenerator(EVENT_CHANNEL, PROPERTIES_READER.getCounts().get(1));
            EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);
            events = eventsGenerator.getEvents();
            EVENT_CHANNEL.registerEvents(events);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

        startSynchronizationThreads();
//        startThreadWithLocks();
//        startThreadWithBlockingQueue();
    }

    private static void outputRegisteredEvents() {
        Thread thread = new Thread(() -> {
            LOGGER.info(Constants.REGISTERED_EVENTS);
            for (SomeEvent event : EVENT_CHANNEL.getEvents()) {
                LOGGER.info(String.format("registered event %s | participants -> %s", event, event.getSubscribers().toString()));
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void outputNewsAboutEvents() {
        Thread thread = new Thread(() -> {
            LOGGER.info(Constants.NEWS_ABOUT_EVENTS);
            for (Map.Entry<SomeEvent, List<String>> entry : EVENT_CHANNEL.getNews().entrySet()) {
                LOGGER.info(String.format("%s->%s", entry.getKey(), entry.getValue().toString()));
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void outputNewsAboutEventsBQ() {
        Thread thread = new Thread(() -> {
            LOGGER.info(Constants.NEWS_ABOUT_EVENTS);
            for (Map<SomeEvent, String> map : EVENT_CHANNEL.getQueue()) {
                for (Map.Entry<SomeEvent, String> entry : map.entrySet()) {
                    LOGGER.info(String.format("%s->%s", entry.getKey(), entry.getValue()));
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static void startSynchronizationThreads() {
        outputRegisteredEvents();

        for (int i = 0; i < PROPERTIES_READER.getCounts().iterator().next(); i++) {
            Publisher publisher = new Publisher(RandomGenerator.eventGeneration(), EVENT_CHANNEL);
            Thread publisherThread = new Thread(publisher);

            publisherThread.start();
            try {
                publisherThread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        outputNewsAboutEvents();

        new Thread(new Subscriber(EVENT_CHANNEL)).start();
    }

    private static void startThreadWithLocks() {
        ReentrantLock reentrantLock = new ReentrantLock();
        outputRegisteredEvents();

        for (int i = 0; i < PROPERTIES_READER.getCounts().iterator().next(); i++) {
            LPublisher publisher = new LPublisher(RandomGenerator.eventGeneration(), EVENT_CHANNEL, reentrantLock);
            Thread publisherThread = new Thread(publisher);

            publisherThread.start();
            try {
                publisherThread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        outputNewsAboutEvents();

        new Thread(new LSubscriber(EVENT_CHANNEL, reentrantLock)).start();
    }

    private static void startThreadWithBlockingQueue() {
        outputRegisteredEvents();

        for (int i = 0; i < PROPERTIES_READER.getCounts().iterator().next(); i++) {
            BQPublisher publisher = new BQPublisher(RandomGenerator.eventGeneration(), EVENT_CHANNEL);
            Thread publisherThread = new Thread(publisher);

            publisherThread.start();
            try {
                publisherThread.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        outputNewsAboutEventsBQ();

        new Thread(new BQSubscriber(EVENT_CHANNEL)).start();
    }
}
