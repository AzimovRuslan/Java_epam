import epam.lab.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class Runner {
    public static void main(String[] args) throws IOException {
        final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
        Publisher publisher = new Publisher(new Event("rock festival", "rock festival starts at 9pm"));
        EventChannel event = new EventChannel(publisher);
        Subscriber subscriber = new Subscriber(event);
        LOGGER.info(subscriber.readEventMessage());

        PropertiesReader propertiesReader = new PropertiesReader();
        propertiesReader.readValues();

        for (Object s : propertiesReader.getValues()) {
            System.out.println(s);
        }
    }
}