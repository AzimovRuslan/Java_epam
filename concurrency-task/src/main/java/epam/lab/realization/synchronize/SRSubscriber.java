package epam.lab.realization.synchronize;

import epam.lab.ArticleChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SRSubscriber implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SRSubscriber.class);

    private String name;
    private ArticleChannel channel;
    private String publisherName;

    public SRSubscriber(String name, ArticleChannel channel, String publisherName) {
        this.name = name;
        this.channel = channel;
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getName() {
        return name;
    }

    public ArticleChannel getChannel() {
        return channel;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                channel.getArticleFromList(this);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        return "Subscriber " + name + " subscribed to " + publisherName;
    }
}
