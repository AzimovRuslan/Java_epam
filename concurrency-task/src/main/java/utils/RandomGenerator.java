package utils;

import epam.lab.Event;

public class RandomGenerator {
    private static final String[] events = {"Rock festival", "Weapons exhibition", "Modern art museum", "Cinema"};

    public static Event eventGeneration() {
        int randomElement = generationRandomNumber(0, events.length);

        return new Event(events[randomElement]);
    }

    public static String messageGeneration() {
        int randomTime = generationRandomNumber(6, 12);

        return "Event start at " + randomTime + " pm";
    }

    private static int generationRandomNumber(int start, int finish) {
        int startNumber = start;
        int finishNumber = finish;
        return startNumber + (int) (Math.random() * finishNumber);
    }
}
