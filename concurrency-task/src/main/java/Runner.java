import epam.lab.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesReader;
import utils.RandomGenerator;

import java.io.IOException;
import java.util.*;

public class Runner {
    public static void main(String[] args) throws IOException {
        final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
//        Publisher publisher = new Publisher(new Event("rock festival", "rock festival starts at 9pm"));
//        EventChannel event = new EventChannel(publisher);
//        Subscriber subscriber = new Subscriber(event);
//        LOGGER.info(subscriber.readEventMessage());

        PropertiesReader propertiesReader = new PropertiesReader();
        propertiesReader.readValues();
        propertiesReader.fillCounts();

        List<Subscriber> subscribers = new ArrayList<>();
        Map<Event, String> publishers = new LinkedHashMap<>();

        for (int i = 0; i < propertiesReader.getCounts().get(1); i++) {
            subscribers.add(new Subscriber(RandomGenerator.eventGeneration()));
        }

        for (Subscriber subscriber : subscribers) {
            System.out.println(subscriber);
        }

        for (int i = 0; i < propertiesReader.getCounts().get(0); i++) {
            publishers.put(RandomGenerator.eventGeneration(), RandomGenerator.messageGeneration());
        }

        System.out.println("---------------------------------------");

        for (Map.Entry<Event, String> entry : publishers.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("---------------------------------------");

        for (Subscriber subscriber : subscribers) {
            for (Map.Entry<Event, String> entry : publishers.entrySet()) {
                if (subscriber.getEvent().getName().equals(entry.getKey().getName())) {
                    System.out.println("Sub (" + subscriber + ") -> " + entry.getValue());
                }
            }
        }
    }
}