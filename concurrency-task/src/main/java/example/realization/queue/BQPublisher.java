package example.realization.queue;

import annotations.Event;
import epam.lab.realization.queue.BQRPublisher;
import example.EventChannel;
import example.events.SomeEvent;
import example.realization.synchronize.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RandomGenerator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BQPublisher extends Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BQRPublisher.class);
    private BlockingQueue<Map.Entry<SomeEvent, List<String>>> queue;

    public BQPublisher(SomeEvent event, EventChannel eventChannel) {
        super(event, eventChannel);
        queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        for (SomeEvent someEvent : getEventChannel().getEvents()) {
            String someEventId = someEvent.getClass().getAnnotation(Event.class).id();
            String eventId = getEvent().getClass().getAnnotation(Event.class).id();

            if (someEventId.equals(eventId)) {
                String news = RandomGenerator.articleGeneration();

                getEventChannel().registerNewsAboutEventInBQ(someEvent, news);
                LOGGER.info(String.format("register news (%s) for " + someEvent.getClass(), news));
            }
        }
    }
}
