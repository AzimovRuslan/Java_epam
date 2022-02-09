package utils;

import constatnts.Constants;
import epam.lab.events.SomeEvent;

public class RandomGenerator {
    private static int number = 0;

    private RandomGenerator() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static String subscriberNameGeneration() {
        int randomElement = generationRandomNumber(0, Constants.RANDOM_SUBSCRIBER_NAMES.length);
        return Constants.RANDOM_SUBSCRIBER_NAMES[randomElement];
    }

    public static String messageGeneration() {
        number++;
        return Constants.SOME_MESSAGE + number;
    }

    public static int generationRandomNumber(int startNumber, int finishNumber) {
        return startNumber + (int) (Math.random() * finishNumber);
    }

    public static SomeEvent eventGeneration() {
        int randomElement = generationRandomNumber(0, Constants.RANDOM_EVENT.length);

        return Constants.RANDOM_EVENT[randomElement];
    }

    public static String subscriberSurnameGeneration() {
        int randomElement = generationRandomNumber(0, Constants.RANDOM_SUBSCRIBER_SURNAMES.length);

        return Constants.RANDOM_SUBSCRIBER_SURNAMES[randomElement];
    }
}
