package epam.lab;

public class Event {
    private String name;


    public Event(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
