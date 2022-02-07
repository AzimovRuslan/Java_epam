package epam.lab.realization.lock;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class LRSubscriber extends SRSubscriber {
    private final ReentrantLock locker;
    private static final Logger LOGGER = LoggerFactory.getLogger(LRSubscriber.class);

    public LRSubscriber(String name, ArticleChannel channel, String publisherName, ReentrantLock locker) {
        super(name, channel, publisherName);
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            Thread.sleep(1000);
            getChannel().getArticleFromList(this);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }
}
