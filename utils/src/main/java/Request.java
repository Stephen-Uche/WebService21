import java.util.HashMap;
import java.util.Map;

public class Request {
    HTTPType type;
    String url;
    Map<String,String> urlParams = new HashMap<>();
}

enum HTTPType {
    GET,
    HEAD,
    POST
}