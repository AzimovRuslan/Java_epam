package example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EventChannel {
    private List<SomeEvent> events;
    private ConcurrentMap<SomeEvent, List<String>> news;

    public EventChannel() {
        events = new ArrayList<>();
        news = new ConcurrentHashMap<>();
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

    public void registerNews(SomeEvent event, List<String> messages) {
        news.put(event, messages);
    }


}