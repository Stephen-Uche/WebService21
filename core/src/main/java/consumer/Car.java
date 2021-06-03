package consumer;

import lombok.Data;

@Data
public class Car {
    private String type;
    private String color;
    private boolean hitchHook;
    private int modelYear;
}

//public record Car(String type, String color, boolean hitch, int modelYear) {
//}
