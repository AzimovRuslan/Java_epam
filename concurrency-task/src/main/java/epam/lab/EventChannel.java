package epam.lab;

public class EventChannel {
    private Publisher publisher;
    private Event event;

    public EventChannel(Publisher publisher) {
        this.publisher = publisher;
        event = publisher.getEvent();
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return String.valueOf(event);
    }
}
