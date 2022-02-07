package epam.lab.realization.synchronize;

import epam.lab.ArticleChannel;

public class SRPublisher implements Runnable {

    private final String name;
    private final ArticleChannel channel;
    private final String article;

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
            channel.publishedArticleList(this);
        }
    }
}
