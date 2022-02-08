import example.*;
import example.events.SomeEvent;
import example.realization.synchronize.Publisher;
import example.realization.synchronize.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EventsGenerator;
import utils.PropertiesReader;
import utils.RandomGenerator;
import utils.SubscribersGenerator;
import java.util.List;
import java.util.Map;

public class SynchronizeRunner {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(SynchronizeRunner.class);
        PropertiesReader propertiesReader = new PropertiesReader();
        EventChannel eventChannel = new EventChannel();
        List<SomeEvent> events;

        SubscribersGenerator subscribersGenerator = new SubscribersGenerator(eventChannel);
        EventsGenerator eventsGenerator = new EventsGenerator(subscribersGenerator);
        events = eventsGenerator.getEvents();
        eventChannel.registerEvents(events);

        Thread t1 = new Thread(() -> {
            for (SomeEvent event : eventChannel.getEvents()) {
                LOGGER.info("registered event " + String.valueOf(event) + "| participants -> " + event.getSubscribers());
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < propertiesReader.getCounts().get(0); i++) {
            Publisher publisher = new Publisher(RandomGenerator.eventGeneration(), eventChannel);
            Thread t = new Thread(publisher);

            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        Thread thread = new Thread(() -> {
            for (Map.Entry<SomeEvent, List<String>> entry : eventChannel.getNews().entrySet()) {
                LOGGER.info(entry.getKey() + "->" + entry.getValue());
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
            Thread.currentThread().interrupt();
        }

        new Thread(new Subscriber(eventChannel)).start();
    }
}
