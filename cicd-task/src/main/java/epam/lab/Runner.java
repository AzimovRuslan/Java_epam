package epam.lab;

public class Runner {
    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        greeter.logging(MessageReader.getMessages());
    }
}
