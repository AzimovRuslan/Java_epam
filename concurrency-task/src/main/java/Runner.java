import example.*;
import utils.PropertiesReader;
import utils.RandomGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        EventChannel eventChannel = new EventChannel();
        List<SomeEvent> events = new ArrayList<>();
        PropertiesReader propertiesReader = new PropertiesReader();
        List<Subscriber> randomSubscribers = new ArrayList<>();

        Thread thread1 = new Thread(() -> {
            try {
                propertiesReader.readValues();
            } catch (IOException e) {
                e.printStackTrace();
            }
            propertiesReader.fillCounts();
        });
        thread1.start();
        thread1.join();

        Thread thread = new Thread(() -> {
            List<Subscriber> rockSubscribers = new ArrayList<>();
            List<Subscriber> museumSubscribers = new ArrayList<>();

            RockFestival rockFestival = new RockFestival();
            ArtMuseum artMuseum = new ArtMuseum();

            int numberRockFestivalSubscribers = RandomGenerator.generationRandomNumber(0, propertiesReader.getCounts().get(1) + 1);
            int numberArtMuseumSubscribers = propertiesReader.getCounts().get(1) - numberRockFestivalSubscribers;

            while (rockSubscribers.size() < numberRockFestivalSubscribers) {
                rockSubscribers.add(new Subscriber(RandomGenerator.subscriberNameGeneration(), eventChannel));
            }

            while (museumSubscribers.size() < numberArtMuseumSubscribers) {
                museumSubscribers.add(new Subscriber(RandomGenerator.subscriberNameGeneration(), eventChannel));
            }

            for (int i = 0; i < rockSubscribers.size(); i++) {
                rockFestival.subscribe(rockSubscribers.get(i));
            }

            for (int i = 0; i < museumSubscribers.size(); i++) {
                artMuseum.subscribe(museumSubscribers.get(i));
            }

            events.add(rockFestival);
            events.add(artMuseum);

            eventChannel.registerEvents(events);
        });
        thread.start();
        thread.join();

        for (int i = 0; i < propertiesReader.getCounts().get(0); i++) {
            Publisher publisher = new Publisher(RandomGenerator.eventGeneration(), eventChannel);
            Thread t = new Thread(publisher);
            t.start();
            t.join();
        }

        new Thread(() -> {
            for (Map.Entry<SomeEvent, List<String>> entry : eventChannel.getNews().entrySet()) {
                System.out.println(entry.getKey() + "->" + entry.getValue());
            }
        }).start();
//        for (example.SomeEvent event : eventChannel.getEvents()) {
//            System.out.println(event);

//        example.Publisher publisher = new example.Publisher(rockFestival, eventChannel);

//        for (Class clazz : publisher.getAnnotatedClasses()) {
//            System.out.println(clazz.getAnnotation(clazz));
//        }

//        Thread t = new Thread(publisher )

//        for (example.SomeEvent event : eventChannel.getEvents()) {
//            System.out.println(event);
//        }

        System.out.println("----------------------------------");
        //subscribers

//        for (Method m : example.RockFestival.class.getMethods()) {
//            Event event = m.getAnnotation(Event.class);
//            if (event != null) {
//                System.out.println(event.id());
//            }
//        }
    }
}
