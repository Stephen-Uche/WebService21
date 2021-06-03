package consumer;

import com.google.gson.Gson;
import spi.Adress;
import spi.Greeting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ServiceLoader;

import static java.util.Objects.nonNull;

public class Main {

    public static void main(String[] args) {

        Gson gson = new Gson();

        ServiceLoader<Greeting> greetings = ServiceLoader.load(Greeting.class);

        for (Greeting greeting : greetings) {
            Adress annotation = greeting.getClass().getAnnotation(Adress.class);
            if (nonNull(annotation) && annotation.value().equals("/Stockholm")) {
                System.out.println(greeting.greeting("Martin"));
            }
        }
    }
}
