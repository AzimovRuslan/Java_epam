package utils;

import constatnts.Constants;

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
        int randomElement = generationRandomNumber(0, Constants.ARTICLES.length - 1);

        return Constants.ARTICLES[randomElement];
    }

    private static int generationRandomNumber(int startNumber, int finishNumber) {
        return startNumber + (int) (Math.random() * finishNumber);
    }
}
