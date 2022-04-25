package utils;

import epam.lab.events.ArtMuseum;
import epam.lab.events.RockFestival;
import epam.lab.events.SomeEvent;

import java.util.ArrayList;
import java.util.List;

public class EventsGenerator {
    private final List<SomeEvent> events;
    private final SubscribersGenerator subscribersGenerator;

    public List<SomeEvent> getEvents() {
        return events;
    }

    public EventsGenerator(SubscribersGenerator subscribersGenerator) {
        this.subscribersGenerator = subscribersGenerator;
        events = new ArrayList<>();
        eventsGeneration();
    }

    private void eventsGeneration() {
        RockFestival rockFestival = new RockFestival();
        ArtMuseum artMuseum = new ArtMuseum();

        for (int i = 0; i < subscribersGenerator.getRockSubscribers().size(); i++) {
            rockFestival.subscribe(subscribersGenerator.getRockSubscribers().get(i));
        }

        for (int i = 0; i < subscribersGenerator.getMuseumSubscribers().size(); i++) {
            artMuseum.subscribe(subscribersGenerator.getMuseumSubscribers().get(i));
        }

        events.add(rockFestival);
        events.add(artMuseum);
    }
}
