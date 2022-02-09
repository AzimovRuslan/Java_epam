package epam.lab.realization.queue;

import epam.lab.EventChannel;
import epam.lab.realization.synchronize.Subscriber;

public class BQSubscriber extends Subscriber {
    public BQSubscriber(EventChannel eventChannel) {
        super(eventChannel);
    }

    @Override
    public void run() {
        getEventChannel().getNewsAboutEventFromBQ();
    }
}
