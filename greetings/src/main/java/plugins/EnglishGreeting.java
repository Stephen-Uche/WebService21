package plugins;

import spi.Greeting;

public class EnglishGreeting implements Greeting {
    @Override
    public String greeting(String name) {
        return "Hello " + name;
    }
}
