package constatnts;

import epam.lab.events.ArtMuseum;
import epam.lab.events.RockFestival;
import epam.lab.events.SomeEvent;

public class Constants {
    public static final String[] RANDOM_SUBSCRIBER_NAMES = {"Pavel", "Vadim", "Artem", "Ura", "Vlad", "Egor", "Nikita", "Kostya"};
    public static final String[] RANDOM_SUBSCRIBER_SURNAMES = {"Azimov", "Perunov", "Lobanov", "Kirik", "Lepeev", "Shutov", "Chikanov", "Eremenko", "Askerko"};
    public static final SomeEvent[] RANDOM_EVENT = {new RockFestival(), new ArtMuseum()};
    public static final String SOME_MESSAGE = "some message №";
    public static final String REGISTERED_EVENTS = "REGISTERED EVENTS";
    public static final String NEWS_ABOUT_EVENTS = "NEWS ABOUT EVENTS PUBLISHED BY PUBLISHERS";
}
