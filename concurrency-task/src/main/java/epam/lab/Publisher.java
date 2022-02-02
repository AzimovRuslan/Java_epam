package epam.lab;

public class Publisher {
    private Event event;
    private String message;

    public Publisher(Event event, String message) {
        this.event = event;
        this.message = message;
    }

    public Event getEvent() {
        return event;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.valueOf(event);
    }
}
