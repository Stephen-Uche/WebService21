package consumer;

import com.google.gson.Gson;
import spi.Greeting;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();

        ServiceLoader<Greeting> greetings = ServiceLoader.load(Greeting.class);

        for (Greeting greeting : greetings) {
            System.out.println(greeting.greeting("Martin"));
        }
    }
}
