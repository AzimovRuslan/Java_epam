package epam.lab.realization.queue;

import epam.lab.realization.synchronize.SRPublisher;

public class BQRPublisher implements Runnable {
    private final SRPublisher publisher;

    public BQRPublisher(SRPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void run() {
        publisher.getChannel().publishedArticleBlockingQueue(publisher);
    }
}
