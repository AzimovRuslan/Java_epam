package epam.lab.events;

import epam.lab.realization.synchronize.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class SomeEvent {
    private List<Subscriber> subscribers;

    public SomeEvent() {
        subscribers = new ArrayList<>();
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
