package epam.lab;

import epam.lab.events.SomeEvent;
import epam.lab.realization.synchronize.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class EventChannel {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventChannel.class);
    private List<SomeEvent> events;
    private final ConcurrentMap<SomeEvent, List<String>> news;
    private final BlockingQueue<ConcurrentMap<SomeEvent, String>> queue;

    public EventChannel() {
        events = new ArrayList<>();
        news = new ConcurrentHashMap<>();
        queue = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<ConcurrentMap<SomeEvent, String>> getQueue() {
        return queue;
    }

    public List<SomeEvent> getEvents() {
        return events;
    }

    public ConcurrentMap<SomeEvent, List<String>> getNews() {
        return news;
    }

    public void registerEvents(List<SomeEvent> eventList) {
        events = eventList;
    }

    public void registerNewsAboutEventInBQ(SomeEvent event, String message) {
        ConcurrentMap<SomeEvent, String> map = new ConcurrentHashMap<>();
        map.put(event, message);
        try {
            queue.put(map);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void getNewsAboutEventFromBQ() {
        try {
            while (!queue.isEmpty()) {
                Map<SomeEvent, String> map = queue.take();

                for (Map.Entry<SomeEvent, String> entry : map.entrySet()) {
                    for (Subscriber sub : entry.getKey().getSubscribers()) {
                        LOGGER.info(String.format("%s got a message about %s->%s", sub, entry.getKey().getClass().toString(), entry.getValue()));

                    }
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void registerNewsAboutEvent(SomeEvent event, List<String> messages) {
        synchronized (this) {
            news.put(event, messages);
        }
    }

    public void getNewsAboutEvent() {
        synchronized (this) {
            for (Map.Entry<SomeEvent, List<String>> entry : news.entrySet()) {
                for (Subscriber sub : entry.getKey().getSubscribers()) {
                    LOGGER.info(String.format("%s got a message about %s->%s", sub, entry.getKey().getClass().toString(), entry.getValue()));
                }
            }
        }
    }
}