package epam.lab;

public class Publisher {
    private Event event;

    public Publisher(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return String.valueOf(event);
    }
}
