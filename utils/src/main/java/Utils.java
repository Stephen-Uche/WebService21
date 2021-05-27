import java.util.Locale;

public class Utils {

    public static String parseUrl(String input) {
//        int firstSpace = input.indexOf(' ') + 1;
//        int secondSpace = input.indexOf(' ', firstSpace);
//
//        return input.substring(firstSpace, secondSpace);

        String[] result = input.split(" ");
        return result[1];
    }

    public static String parseHttpRequestType(String input) {
        return input.substring(0,input.indexOf(' '));
    }


    public String message() {
        return "Hello from Utils";
    }

}
