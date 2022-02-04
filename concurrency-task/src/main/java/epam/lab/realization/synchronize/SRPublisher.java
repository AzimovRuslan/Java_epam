package epam.lab.realization.synchronize;

import epam.lab.ArticleChannel;


public class SRPublisher implements Runnable{
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
        channel.publishedArticleList(this);
    }
}
