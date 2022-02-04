package epam.lab.realization.synchronize;

import epam.lab.ArticleChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SRPublisher implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(SRPublisher.class);

    private String name;
    private ArticleChannel channel;
    private String article;

    public SRPublisher(String name, ArticleChannel channel, String article) {
        this.name = name;
        this.channel = channel;
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public ArticleChannel getChannel() {
        return channel;
    }

    public String getArticle() {
        return article;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                channel.publishedArticleList(this);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
