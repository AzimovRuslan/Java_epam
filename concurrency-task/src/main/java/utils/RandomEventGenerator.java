package utils;

public class RandomEventGenerator {
    private static final String[] events = {"Rock festival", "Weapons exhibition", "Modern art museum", "Cinema"};

    public String eventGeneration() {
        int startNumber = 0;
        int finishNumber = events.length;
        int randomElement = startNumber + (int) (Math.random() * finishNumber);

        return events[randomElement];
    }
}
