package example;

import annotations.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Publisher implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);
    private SomeEvent event;
    private EventChannel eventChannel;

    public Publisher(SomeEvent event, EventChannel eventChannel) {
        this.event = event;
        this.eventChannel = eventChannel;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (SomeEvent someEvent : eventChannel.getEvents()) {
                String someEventId = someEvent.getClass().getAnnotation(Event.class).id();
                String eventId = event.getClass().getAnnotation(Event.class).id();
                Map<SomeEvent, List<String>> newsMap = eventChannel.getNews();

                if (someEventId.equals(eventId)) {
                    String news = RandomGenerator.articleGeneration();
                    List<String> list = new ArrayList<>();

                    for (Map.Entry<SomeEvent, List<String>> entry : newsMap.entrySet()) {
                        if (entry.getKey().getClass().equals(someEvent.getClass())) {
                            list = entry.getValue();
                        }
                    }

                    list.add(news);

                    eventChannel.registerNews(someEvent, list);
                    LOGGER.info(String.format("register news (%s) for " + someEvent.getClass(), news));
                }
            }
        }
    }
}
