package utils;

import constatnts.Constants;
import example.SomeEvent;

public class RandomGenerator {
    public static String publisherNameGeneration() {
        int randomElement = generationRandomNumber(0, Constants.PUBLISHER_NAMES.length);

        return Constants.PUBLISHER_NAMES[randomElement];
    }

    public static String subscriberNameGeneration() {
        int randomElement = generationRandomNumber(0, Constants.SUBSCRIBER_NAMES.length);
        return Constants.SUBSCRIBER_NAMES[randomElement];
    }

    public static String articleGeneration() {
        int randomElement = generationRandomNumber(0, Constants.ARTICLES.length);

        return Constants.ARTICLES[randomElement];
    }

    public static int generationRandomNumber(int startNumber, int finishNumber) {
        return startNumber + (int) (Math.random() * finishNumber);
    }

    public static SomeEvent eventGeneration() {
        int randomElement = generationRandomNumber(0, Constants.RandomEvent.length);

        return Constants.RandomEvent[randomElement];
    }
}
