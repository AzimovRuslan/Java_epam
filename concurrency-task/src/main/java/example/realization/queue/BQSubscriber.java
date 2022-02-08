package example.realization.queue;

import example.EventChannel;
import example.realization.synchronize.Subscriber;

public class BQSubscriber extends Subscriber {
    public BQSubscriber(String name, String surname, EventChannel eventChannel) {
        super(name, surname, eventChannel);
    }

    public BQSubscriber(EventChannel eventChannel) {
        super(eventChannel);
    }

    @Override
    public void run() {
        getEventChannel().getNewsAboutEventFromBQ();
    }
}
