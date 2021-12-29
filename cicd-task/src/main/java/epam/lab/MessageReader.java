package epam.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageReader {
    private MessageReader() { }

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReader.class);

    public static List<String> getMessages() {
        List<String> messages = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(Constants.PATH))) {
            while(sc.hasNextLine()) {
                messages.add(sc.next());
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(Constants.FILE_NOT_FOUND);
        }
        return messages;
    }
}
