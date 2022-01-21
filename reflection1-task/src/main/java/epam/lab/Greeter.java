package epam.lab;

import annotations.InjectDependency;
import annotations.Service;
import interfaces.StringCreator;

@Service(id = Constants.GREETER, dependsOn = {Constants.RANDOM_GENERATOR})
public class Greeter implements StringCreator {
    private int randomNumber;

    @InjectDependency
    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public String stringCreate() {
        RandomGenerator randomGenerator = new RandomGenerator();
        setRandomNumber(randomGenerator.generation());
        return (Constants.HELLO_WORLD + randomNumber);
    }
}
