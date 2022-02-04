package epam.lab.realization.lock;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class LRPublisher extends SRPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(LRPublisher.class);
    private final ReentrantLock locker;

    public LRPublisher(String name, ArticleChannel channel, String article, ReentrantLock locker) {
        super(name, channel, article);
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            Thread.sleep(1000);
            getChannel().publishedArticleList(this);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            locker.unlock();
        }
    }
}
