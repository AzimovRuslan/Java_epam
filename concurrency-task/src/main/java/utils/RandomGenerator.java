package utils;

public class RandomGenerator {
    private static final String[] publisherNames = {"Ruslan", "Kirill", "Vadim"};
    private static final String[] subscriberNames = {"Pavel", "Vadim", "Artem", "Veronika", "Kate", "Valeriy"};
    private static final String[] articles = {"Animals", "Flowers", "I love java", "EPAM", "Minsk", "High school"};

    public static String publisherNameGeneration() {
        int randomElement = generationRandomNumber(0, publisherNames.length);

        return publisherNames[randomElement];
    }

    public static String subscriberNameGeneration() {
        int randomElement = generationRandomNumber(0, subscriberNames.length);

        return subscriberNames[randomElement];
    }

    public static String articleGeneration() {
        int randomElement = generationRandomNumber(0, articles.length - 1);

        return articles[randomElement];
    }

    private static int generationRandomNumber(int startNumber, int finishNumber) {
        return startNumber + (int) (Math.random() * finishNumber);
    }
}
