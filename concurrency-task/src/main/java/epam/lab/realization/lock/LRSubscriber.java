package epam.lab.realization.lock;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRSubscriber;

import java.util.concurrent.locks.ReentrantLock;

public class LRSubscriber extends SRSubscriber {
    private final ReentrantLock locker;

    public LRSubscriber(String name, ArticleChannel channel, String publisherName, ReentrantLock locker) {
        super(name, channel, publisherName);
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            getChannel().getArticleFromList(this);
        } finally {
            locker.unlock();
        }
    }
}
