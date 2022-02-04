package epam.lab.realization.queue;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRPublisher;
import epam.lab.realization.synchronize.SRSubscriber;

import java.util.concurrent.BlockingQueue;

public class BQRSubscriber extends SRSubscriber {
    public BQRSubscriber(String name, ArticleChannel channel, String publisherName) {
        super(name, channel, publisherName);
    }

    @Override
    public void run() {
        try {
            BlockingQueue<SRPublisher> blockingQueuePublishers = getChannel().getBlockingQueuePublishers();
            Thread.sleep(1000);
            if (blockingQueuePublishers.take().getName().equals(this.getPublisherName())) {
                blockingQueuePublishers.take();
            }
//            getChannel().getArticleFromBlockingQueue(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
