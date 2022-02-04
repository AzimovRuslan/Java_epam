import epam.lab.*;
import epam.lab.realization.queue.BQRPublisher;
import epam.lab.realization.queue.BQRSubscriber;
import epam.lab.realization.synchronize.SRPublisher;
import epam.lab.realization.synchronize.SRSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesReader;
import utils.RandomGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Runner {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

        ArticleChannel channel = new ArticleChannel();

        PropertiesReader propertiesReader = new PropertiesReader();
        propertiesReader.readValues();
        propertiesReader.fillCounts();


        final List<SRPublisher> publishers = new ArrayList<>();

        Thread t = new Thread(() -> {
            for (int i = 0; i < propertiesReader.getCounts().iterator().next(); i++) {
                publishers.add(new SRPublisher(RandomGenerator.publisherNameGeneration(), channel, RandomGenerator.articleGeneration()));
            }
        });
        t.start();
        t.join();

        for (SRPublisher publisher : publishers) {
            Thread t1 = new Thread(publisher);
            t1.start();
            t1.join();
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("опубликованные статьи");
        for (SRPublisher publisher : channel.getPublishers()) {
            System.out.println(publisher.getName() + " -> " + publisher.getArticle());
        }
        System.out.println("---------------------------------------------------------");

        for (SRPublisher publisher : publishers) {
            Thread t1 = new Thread(new BQRPublisher(publisher));
            t1.start();
            t1.join();
        }

        for (SRPublisher publisher : publishers) {
            BQRSubscriber subscriber = new BQRSubscriber(RandomGenerator.subscriberNameGeneration(), channel, publisher.getName());
            System.out.println(subscriber);
            Thread t2 = new Thread(subscriber);
            t2.start();
            t2.join();
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------");

        for (SRPublisher publisher : publishers) {
            SRSubscriber subscriber = new SRSubscriber(RandomGenerator.subscriberNameGeneration(), channel, publisher.getName());
            System.out.println(subscriber);
            Thread t2 = new Thread(subscriber);
            t2.start();
            t2.join();
        }

        for (int i = 0; i < propertiesReader.getCounts().get(0); i++) {
            SRPublisher publisher = new SRPublisher(RandomGenerator.publisherNameGeneration(), channel, RandomGenerator.articleGeneration());

            Thread t1 = new Thread(new BQRPublisher(publisher));
            t1.start();
        }

        for (int i = 0; i < propertiesReader.getCounts().get(1); i++) {
            BQRSubscriber subscriber = new BQRSubscriber(RandomGenerator.subscriberNameGeneration(), channel, RandomGenerator.publisherNameGeneration());
            System.out.println(subscriber);
            Thread t2 = new Thread(subscriber);
            t2.start();
        }




















//        Publisher publisher = new Publisher(new Event("rock festival", "rock festival starts at 9pm"));
//        EventChannel event = new EventChannel(publisher);
//        Subscriber subscriber = new Subscriber(event);
//        LOGGER.info(subscriber.readEventMessage());

//        PropertiesReader propertiesReader = new PropertiesReader();
//        propertiesReader.readValues();
//        propertiesReader.fillCounts();
//
//        List<Subscriber> subscribers = new ArrayList<>();
//        Map<Event, String> publishers = new LinkedHashMap<>();
//
//        for (int i = 0; i < propertiesReader.getCounts().get(1); i++) {
//            subscribers.add(new Subscriber(RandomGenerator.eventGeneration()));
//        }
//
//        for (Subscriber subscriber : subscribers) {
//            System.out.println(subscriber);
//        }
//
//        for (int i = 0; i < propertiesReader.getCounts().get(0); i++) {
//            publishers.put(RandomGenerator.eventGeneration(), RandomGenerator.messageGeneration());
//        }
//
//        System.out.println("---------------------------------------");
//
//        for (Map.Entry<Event, String> entry : publishers.entrySet()) {
//            System.out.println(entry.getKey() + " -> " + entry.getValue());
//        }
//
//        System.out.println("---------------------------------------");
//
//        for (Subscriber subscriber : subscribers) {
//            for (Map.Entry<Event, String> entry : publishers.entrySet()) {
//                if (subscriber.getEvent().getName().equals(entry.getKey().getName())) {
//                    System.out.println("Sub (" + subscriber + ") -> " + entry.getValue());
//                }
//            }
//        }
//
//        System.out.println("------------------------------------------");
//
//        Publisher publisher = new Publisher(RandomGenerator.eventGeneration(), RandomGenerator.messageGeneration());
//        EventChannel eventChannel = new EventChannel(publisher);
    }
}