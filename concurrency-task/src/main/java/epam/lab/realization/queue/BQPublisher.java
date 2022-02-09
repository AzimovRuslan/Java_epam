package epam.lab.realization.queue;

import annotations.Event;
import epam.lab.EventChannel;
import epam.lab.events.SomeEvent;
import epam.lab.realization.synchronize.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.RandomGenerator;

public class BQPublisher extends Publisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(BQPublisher.class);

    public BQPublisher(SomeEvent event, EventChannel eventChannel) {
        super(event, eventChannel);
    }

    @Override
    public void run() {
        for (SomeEvent someEvent : getEventChannel().getEvents()) {
            String someEventId = someEvent.getClass().getAnnotation(Event.class).id();
            String eventId = getEvent().getClass().getAnnotation(Event.class).id();

            if (someEventId.equals(eventId)) {
                String news = RandomGenerator.messageGeneration();

                getEventChannel().registerNewsAboutEventInBQ(someEvent, news);

                LOGGER.info(String.format("added news (%s) for event (%s)", news, someEvent.getClass().toString()));
            }
        }
    }
}
