package epam.lab;

import epam.lab.realization.queue.BQRSubscriber;
import epam.lab.realization.synchronize.SRPublisher;
import epam.lab.realization.synchronize.SRSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArticleChannel {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleChannel.class);
    private List<SRPublisher> publishers;
    private BlockingQueue<SRPublisher> blockingQueuePublishers;

    public ArticleChannel() {
        publishers = new ArrayList<>();
        blockingQueuePublishers = new ArrayBlockingQueue<>(1, true);
    }

    /**
     *Published article in Map
     */
    public void publishedArticleList(SRPublisher publisher) {
            publishers.add(publisher);
            LOGGER.info(String.format("%s published an publication %s", publisher.getName(), publisher.getArticle()));
    }

    /**
     * Get article from Map
     */
    public void getArticleFromList(SRSubscriber subscriber) {
            for (SRPublisher publisher : publishers) {
                if (publisher.getName().equals(subscriber.getPublisherName())) {
                    LOGGER.info(String.format("%s received the article %s", subscriber, publisher.getArticle()));
                }
            }
    }

    /**
     * Published article in blocking queue
     */
    public void publishedArticleBlockingQueue(SRPublisher publisher) {
            blockingQueuePublishers.add(publisher);
            LOGGER.info(String.format("%s published an article %s", publisher.getName(), publisher.getArticle()));
    }

    /**
     * Get article from blocking queue
     */
    public void getArticleFromBlockingQueue(BQRSubscriber subscriber) throws InterruptedException {
            LOGGER.info(subscriber + " received the article " + blockingQueuePublishers.take().getArticle());
    }
}