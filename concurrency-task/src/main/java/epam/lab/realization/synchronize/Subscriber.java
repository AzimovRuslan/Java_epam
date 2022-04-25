package epam.lab.realization.synchronize;

import epam.lab.EventChannel;

public class Subscriber implements Runnable {
    private String name;
    private String surname;
    private final EventChannel eventChannel;

    public Subscriber(String name, String surname, EventChannel eventChannel) {
        this.name = name;
        this.surname = surname;
        this.eventChannel = eventChannel;
    }

    public EventChannel getEventChannel() {
        return eventChannel;
    }

    public Subscriber(EventChannel eventChannel) {
        this.eventChannel = eventChannel;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public void run() {
        synchronized (this) {
            eventChannel.getNewsAboutEvent();
        }
    }
}
