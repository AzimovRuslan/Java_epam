package epam.lab;

public class Subscriber {
    private Event event;

    public Subscriber(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return String.valueOf(event);
    }

    //    public String readEventMessage() {
//        return String.valueOf(event);
//    }
}
