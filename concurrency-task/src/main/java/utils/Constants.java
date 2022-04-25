package utils;

import epam.lab.events.ArtMuseum;
import epam.lab.events.RockFestival;
import epam.lab.events.SomeEvent;

public class Constants {
    protected static final String[] RANDOM_SUBSCRIBER_NAMES = {"Pavel", "Vadim", "Artem", "Ura", "Vlad", "Egor", "Nikita", "Kostya"};
    protected static final String[] RANDOM_SUBSCRIBER_SURNAMES = {"Azimov", "Perunov", "Lobanov", "Kirik", "Lepeev", "Shutov", "Chikanov", "Eremenko", "Askerko"};
    protected static final SomeEvent[] RANDOM_EVENT = {new RockFestival(), new ArtMuseum()};
    public static final String SOME_MESSAGE = "some message №";
    public static final String REGISTERED_EVENTS = "REGISTERED EVENTS";
    public static final String NEWS_ABOUT_EVENTS = "NEWS ABOUT EVENTS PUBLISHED BY PUBLISHERS";
    public static final String UTILITY_CLASS = "Utility class";

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }
}
