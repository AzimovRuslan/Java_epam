package epam.lab;

public class Event {
    private String name;
    private String message;


    public Event(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " -> "  + message;
    }
}
