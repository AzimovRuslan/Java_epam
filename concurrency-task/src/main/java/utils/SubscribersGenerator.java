package utils;

import epam.lab.ArticleChannel;
import example.*;
import example.realization.synchronize.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SubscribersGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleChannel.class);
    private PropertiesReader propertiesReader;
    private List<Subscriber> rockSubscribers;
    private List<Subscriber> museumSubscribers;
    private EventChannel eventChannel;

    public SubscribersGenerator(EventChannel eventChannel) {
        propertiesReader = new PropertiesReader();
        rockSubscribers = new ArrayList<>();
        museumSubscribers = new ArrayList<>();
        this.eventChannel = eventChannel;
        subscribersGeneration();
    }

    public List<Subscriber> getMuseumSubscribers() {
        return museumSubscribers;
    }

    public List<Subscriber> getRockSubscribers() {
        return rockSubscribers;
    }

    private void subscribersGeneration() {
        Thread subscribersGenerationThread = new Thread(() -> {
            int numberRockFestivalSubscribers = RandomGenerator.generationRandomNumber(0, propertiesReader.getCounts().get(1) + 1);
            int numberArtMuseumSubscribers = propertiesReader.getCounts().get(1) - numberRockFestivalSubscribers;

            while (rockSubscribers.size() < numberRockFestivalSubscribers) {
                rockSubscribers.add(new Subscriber(RandomGenerator.subscriberNameGeneration(), RandomGenerator.subscriberSurnameGeneration(), eventChannel));
            }

            while (museumSubscribers.size() < numberArtMuseumSubscribers) {
                museumSubscribers.add(new Subscriber(RandomGenerator.subscriberNameGeneration(), RandomGenerator.subscriberSurnameGeneration(), eventChannel));
            }
        });

        subscribersGenerationThread.start();
        try {
            subscribersGenerationThread.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
