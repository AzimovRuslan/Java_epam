package epam.lab;

import epam.lab.realization.synchronize.SRPublisher;
import epam.lab.realization.synchronize.SRSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArticleChannel {
    final Logger LOGGER = LoggerFactory.getLogger(ArticleChannel.class);
    private List<SRPublisher> publishers;
    private Map<SRPublisher, String> mapArticles;
    private BlockingQueue<SRPublisher> blockingQueuePublishers;

    public ArticleChannel() {
        publishers = new ArrayList<>();
        mapArticles = new HashMap<>();
        blockingQueuePublishers = new ArrayBlockingQueue<>(1, true);
    }

    public Map<SRPublisher, String> getMapArticles() {
        return mapArticles;
    }

    public BlockingQueue<SRPublisher> getBlockingQueuePublishers() {
        return blockingQueuePublishers;
    }

    public List<SRPublisher> getPublishers() {
        return publishers;
    }

    /**
     *Published article in Map
     * @param publisher
     */
    public void publishedArticleList(SRPublisher publisher) {
        synchronized (this) {
//            mapArticles.put(publisher, article);
            publishers.add(publisher);
            LOGGER.info(String.format("%s published an publication %s", publisher.getName(), publisher.getArticle()));
        }
    }

    /**
     * Get article from Map
     * @param subscriber
     */
    public void getArticleFromList(SRSubscriber subscriber) {
        synchronized (this) {
            for (SRPublisher publisher : publishers) {
                if (publisher.getName().equals(subscriber.getPublisherName())) {
                    LOGGER.info(String.format("%s received the article %s", subscriber, publisher.getArticle()));
                }
            }
//            for (Map.Entry<SRPublisher, String> entry: mapArticles.entrySet()) {
//                if (entry.getKey().getName().equals(subscriber.getPublisherName())) {
//                    LOGGER.info(String.format("%s received the article %s", subscriber, entry.getValue()));
//                }
//            }
        }
    }

    public void publishedArticleBlockingQueue(SRPublisher publisher) throws InterruptedException {
        blockingQueuePublishers.add(publisher);
        LOGGER.info(String.format("%s published an article %s", publisher.getName(), publisher.getArticle()));
    }

//    public void getArticleFromBlockingQueue(SRSubscriber subscriber) throws InterruptedException {
//        if (blockingQueuePublishers.take().getName().equals(subscriber.getPublisherName())) {
//            blockingQueuePublishers.take();
//            LOGGER.info(blockingQueuePublishers.take().getArticle());
//        }
//    }
}
