package epam.lab;

public class Subscriber {
    private Event event;

    public Subscriber(EventChannel eventChannel) {
        event = eventChannel.getEvent();
    }

    public String readEventMessage() {
        return String.valueOf(event);
    }
}
