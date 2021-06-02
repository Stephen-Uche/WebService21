package plugins;

import spi.Greeting;

public class SwedishGreeting implements Greeting {
    @Override
    public String greeting(String name) {
        return "Hej " + name;
    }
}
