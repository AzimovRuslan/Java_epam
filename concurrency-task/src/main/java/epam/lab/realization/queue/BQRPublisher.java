package epam.lab.realization.queue;

import epam.lab.realization.synchronize.SRPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BQRPublisher implements Runnable{
    private SRPublisher publisher;
    private static final Logger LOGGER = LoggerFactory.getLogger(BQRPublisher.class);

    public BQRPublisher(SRPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            publisher.getChannel().publishedArticleBlockingQueue(publisher);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
