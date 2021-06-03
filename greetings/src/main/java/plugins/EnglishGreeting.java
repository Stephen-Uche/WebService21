package plugins;

import spi.Adress;
import spi.Greeting;

@Adress("/London")
public class EnglishGreeting implements Greeting {
    @Override
    public String greeting(String name) {
        return "Hello " + name;
    }
}
