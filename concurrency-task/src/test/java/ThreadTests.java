import epam.lab.ArticleChannel;
import epam.lab.realization.lock.LRPublisher;
import epam.lab.realization.lock.LRSubscriber;
import epam.lab.realization.queue.BQRPublisher;
import epam.lab.realization.queue.BQRSubscriber;
import epam.lab.realization.synchronize.SRPublisher;
import epam.lab.realization.synchronize.SRSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.PropertiesReader;
import utils.RandomGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTests {
    final Logger LOGGER = LoggerFactory.getLogger(Thread.class);
    ArticleChannel channel = new ArticleChannel();
    PropertiesReader propertiesReader = new PropertiesReader();
    final List<SRPublisher> publishers = new ArrayList<>();

    private void fillPublishers() throws IOException, InterruptedException {
        propertiesReader.readValues();
        propertiesReader.fillCounts();

        Thread t = new Thread(() -> {
            for (int i = 0; i < propertiesReader.getCounts().iterator().next(); i++) {
                publishers.add(new SRPublisher(RandomGenerator.publisherNameGeneration(), channel, RandomGenerator.articleGeneration()));
            }
        });
        t.start();
        t.join();
    }

    @Test
    public void synchronizeTests() throws InterruptedException, IOException {
        fillPublishers();

        for (SRPublisher publisher : publishers) {
            Thread t1 = new Thread(publisher);
            t1.start();
            t1.join();
        }

        for (SRPublisher publisher : publishers) {
            SRSubscriber subscriber = new SRSubscriber(RandomGenerator.subscriberNameGeneration(), channel, publisher.getName());
            LOGGER.info(String.valueOf(subscriber));
            Thread t2 = new Thread(subscriber);
            t2.start();
            t2.join();
        }
    }

    @Test
    public void blockingQueueTest() throws IOException, InterruptedException {
        fillPublishers();

        for (SRPublisher publisher : publishers) {
            BQRSubscriber subscriber = new BQRSubscriber(RandomGenerator.subscriberNameGeneration(), channel, publisher.getName());

            Thread t1 = new Thread(new BQRPublisher(publisher));
            Thread t2 = new Thread(subscriber);

            t1.start();
            t1.join();

            t2.start();
            t2.join();
        }
    }

    @Test
    public void lockTest() throws IOException, InterruptedException {
        fillPublishers();
        ReentrantLock locker = new ReentrantLock();

        for (SRPublisher publisher : publishers) {
            Thread t1 = new Thread(new LRPublisher(publisher.getName(), publisher.getChannel(), publisher.getArticle(), locker));
            t1.start();
            t1.join();
        }

        for (SRPublisher publisher : publishers) {
            LRSubscriber subscriber = new LRSubscriber(RandomGenerator.subscriberNameGeneration(), channel, publisher.getName(), locker);
            Thread t2 = new Thread(subscriber);
            t2.start();
            t2.join();
        }
    }
}
