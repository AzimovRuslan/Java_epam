package epam.lab.realization.lock;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRPublisher;

import java.util.concurrent.locks.ReentrantLock;

public class LRPublisher extends SRPublisher {
    private final ReentrantLock locker;

    public LRPublisher(String name, ArticleChannel channel, String article, ReentrantLock locker) {
        super(name, channel, article);
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            getChannel().publishedArticleList(this);
        } finally {
            locker.unlock();
        }
    }
}
