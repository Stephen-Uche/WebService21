public class Utils {

    public static Request parseHttpRequest(String input) {

        var request = new Request();
        request.type = parseHttpRequestType(input);
        request.url = parseUrl(input);
        return request;
    }

    private static String parseUrl(String input) {
//        int firstSpace = input.indexOf(' ') + 1;
//        int secondSpace = input.indexOf(' ', firstSpace);
//        return input.substring(firstSpace, secondSpace);
        String[] result = input.split(" ");
        return result[1];
    }

    private static HTTPType parseHttpRequestType(String input) {
        if (input.startsWith("G"))
            return HTTPType.GET;
        else if (input.startsWith("H"))
            return HTTPType.HEAD;
        else if (input.startsWith("PO"))
            return HTTPType.POST;

        throw new RuntimeException("Invalid type");
    }

    //Example of switch expressions to handle all possible enum cases without needing default case.
    public static String handleRequest(Request request) {
        return switch (request.type) {
            case GET -> "GET";
            case HEAD -> "HEAD";
            case POST -> "POST";
        };
    }

    public String message() {
        return "Hello from Utils";
    }
}
