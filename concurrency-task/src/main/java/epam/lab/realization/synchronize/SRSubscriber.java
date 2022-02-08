package epam.lab.realization.synchronize;

import epam.lab.ArticleChannel;

public class SRSubscriber implements Runnable {
    private final String name;
    private final ArticleChannel channel;
    private final String publisherName;

    public SRSubscriber(String name, ArticleChannel channel, String publisherName) {
        this.name = name;
        this.channel = channel;
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public ArticleChannel getChannel() {
        return channel;
    }

    @Override
    public void run() {
        synchronized (this) {
            channel.getArticleFromList(this);
        }
    }

    @Override
    public String toString() {
        return "example.Subscriber " + name + " subscribed to " + publisherName;
    }
}
