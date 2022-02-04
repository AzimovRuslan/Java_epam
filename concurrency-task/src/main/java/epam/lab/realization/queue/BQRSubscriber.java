package epam.lab.realization.queue;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BQRSubscriber extends SRSubscriber {
    private static final Logger LOGGER = LoggerFactory.getLogger(BQRSubscriber.class);

    public BQRSubscriber(String name, ArticleChannel channel, String publisherName) {
        super(name, channel, publisherName);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            getChannel().getArticleFromBlockingQueue(this);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
