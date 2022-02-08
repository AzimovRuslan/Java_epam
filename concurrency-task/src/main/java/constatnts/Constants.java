package constatnts;

import example.ArtMuseum;
import example.RockFestival;
import example.SomeEvent;

public class Constants {
    public static final String[] PUBLISHER_NAMES = {"Ruslan", "Kirill", "Vadim"};
    public static final String[] SUBSCRIBER_NAMES = {"Pavel", "Vadim", "Artem", "Veronika", "Kate", "Valeriy"};
    public static final String[] ARTICLES = {"Animals", "Flowers", "I love java", "EPAM", "Minsk", "High school"};

    public static final SomeEvent[] RandomEvent = {new RockFestival(), new ArtMuseum()};
    public static final String RECEIVED_ARTICLE = " received the article ";
    public static final String PUBLISHED_ARTICLE = " published an article ";
}
