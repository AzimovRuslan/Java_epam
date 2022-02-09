package utils;
import epam.lab.EventChannel;
import epam.lab.realization.synchronize.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SubscribersGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribersGenerator.class);
    private final List<Subscriber> rockSubscribers;
    private final List<Subscriber> museumSubscribers;
    private final EventChannel eventChannel;
    private final int subCount;


    public SubscribersGenerator(EventChannel eventChannel, int subCount) {
        rockSubscribers = new ArrayList<>();
        museumSubscribers = new ArrayList<>();
        this.eventChannel = eventChannel;
        this.subCount = subCount;
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
            int numberRockFestivalSubscribers = RandomGenerator.generationRandomNumber(0, subCount + 1);
            int numberArtMuseumSubscribers = subCount - numberRockFestivalSubscribers;

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
