package epam.lab.realization.synchronize;

import epam.lab.ArticleChannel;

public class SRSubscriber implements Runnable {
    private String name;
    private ArticleChannel channel;
    private String publisherName;

    public SRSubscriber(String name, ArticleChannel channel, String publisherName) {
        this.name = name;
        this.channel = channel;
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getName() {
        return name;
    }

    public ArticleChannel getChannel() {
        return channel;
    }

    @Override
    public void run() {
        channel.getArticleFromList(this);
    }

    @Override
    public String toString() {
        return "Subscriber " + name + " subscribed to " + publisherName;
    }
}
