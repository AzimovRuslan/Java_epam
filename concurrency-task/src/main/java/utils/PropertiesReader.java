package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);

    Collection<Object> values;

    public void readValues() throws IOException {
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader("src\\main\\resources\\configuration.properties")) {
            properties.load(fileReader);
            values = properties.values();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Collection<Object> getValues() {
        return values;
    }
}
