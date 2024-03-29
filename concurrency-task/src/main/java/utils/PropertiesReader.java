package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
    private static final String PATH = "src\\main\\resources\\configuration.properties";

    private Collection<Object> values;
    private final List<Integer> counts;

    public PropertiesReader() {
        counts = new ArrayList<>();
        readValues();
        fillCounts();
    }

    public void readValues() {
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader(PATH)) {
            properties.load(fileReader);
            values = properties.values();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void fillCounts() {
        for (Object obj : values) {
            String stringCount = (String) obj;
            int count = Integer.parseInt(stringCount);
            counts.add(count);
        }
    }

    public List<Integer> getCounts() {
        return counts;
    }
}
