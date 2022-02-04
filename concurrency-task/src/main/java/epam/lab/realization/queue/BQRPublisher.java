package epam.lab.realization.queue;

import epam.lab.ArticleChannel;
import epam.lab.realization.synchronize.SRPublisher;

import java.util.Map;

public class BQRPublisher implements Runnable{
    private SRPublisher publisher;

    public BQRPublisher(SRPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            publisher.getChannel().publishedArticleBlockingQueue(publisher);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
