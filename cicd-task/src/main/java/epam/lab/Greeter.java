package epam.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

import java.util.List;

public class Greeter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Greeter.class);
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

    public void logging(List<String> messages){
        if (!messages.isEmpty()) {
            for (String s : messages) {
                LOGGER.info(s);
            }
        } else {
            LOGGER.info(Constants.NO_MESSAGES);
        }
        StatusPrinter.print(lc);
    }
}
