package example;

import example.EventChannel;

public class Subscriber {
    private String name;
    private EventChannel eventChannel;

    public Subscriber(String name, EventChannel eventChannel) {
        this.name = name;
        this.eventChannel = eventChannel;
    }

    @Override
    public String toString() {
        return "example.Subscriber{" +
                "name='" + name + '\'' +
                ", eventChannel=" + eventChannel +
                '}';
    }
}
