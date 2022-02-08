package utils;

import example.events.ArtMuseum;
import example.events.RockFestival;
import example.events.SomeEvent;

import java.util.ArrayList;
import java.util.List;

public class EventsGenerator {
    private List<SomeEvent> events;
    private SubscribersGenerator subscribersGenerator;

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
