package epam.lab;

import constatnts.Constants;
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
    private static final int QUEUE_CAPACITY = 1;

    public ArticleChannel() {
        publishers = new ArrayList<>();
        blockingQueuePublishers = new ArrayBlockingQueue<>(QUEUE_CAPACITY, true);
    }

    /**
     *Published article in Map
     */
    public void publishedArticleList(SRPublisher publisher) {
            publishers.add(publisher);
            LOGGER.info(publisher.getName() + Constants.PUBLISHED_ARTICLE + publisher.getArticle());
    }

    /**
     * Get article from Map
     */
    public void getArticleFromList(SRSubscriber subscriber) {
            for (SRPublisher publisher : publishers) {
                if (publisher.getName().equals(subscriber.getPublisherName())) {
                    LOGGER.info(subscriber + Constants.RECEIVED_ARTICLE + publisher.getArticle());
                }
            }
    }

    /**
     * Published article in blocking queue
     */
    public void publishedArticleBlockingQueue(SRPublisher publisher) {
            blockingQueuePublishers.add(publisher);
            LOGGER.info(publisher.getName() + Constants.PUBLISHED_ARTICLE + publisher.getArticle());
    }

    /**
     * Get article from blocking queue
     */
    public void getArticleFromBlockingQueue(BQRSubscriber subscriber) throws InterruptedException {
            LOGGER.info(subscriber + Constants.RECEIVED_ARTICLE + blockingQueuePublishers.take().getArticle());
    }
}