package plugins;

import spi.Adress;
import spi.Greeting;

@Adress("/Stockholm")
public class SwedishGreeting implements Greeting {
    @Override
    public String greeting(String name) {
        return "Hej " + name;
    }
}
