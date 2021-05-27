import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    @Test
    void requestTypeGetWithRootUrl(){
        var request = Utils.parseHttpRequest("""
                GET / HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
      assertThat(request.url).isEqualTo("/");
    }

    @Test
    void requestWithFilePath(){
        var request = Utils.parseHttpRequest("""
                GET /index.html HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.url).isEqualTo("/index.html");
        assertThat(request.type).isEqualTo(HTTPType.GET);
    }

    @Test
    void requestTypeHead() {
        Request request = Utils.parseHttpRequest("""
                HEAD /index.html HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.HEAD);
    }

    @Test
    void requestTypeGetWithOneUrlParameter() {
        Request request = Utils.parseHttpRequest("""
                GET /products?id=23 HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.GET);
        assertThat(request.url).isEqualTo("/products");
        assertThat(request.urlParams).containsEntry("id","23");
    }

    @Test
    void requestTypePOSTWithTwoUrlParameters() {
        Request request = Utils.parseHttpRequest("""
                POST /products?id=23&order=ascend HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.POST);
        assertThat(request.url).isEqualTo("/products");
        assertThat(request.urlParams).containsEntry("id","23").containsEntry("order","ascend");
    }

    @Test
    void requestTypeGETWithUrlParameterIncludingSpace() {
        Request request = Utils.parseHttpRequest("""
                GET /products?text=Hello+there HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.GET);
        assertThat(request.url).isEqualTo("/products");
        assertThat(request.urlParams).containsEntry("text","Hello there");
    }

    @Test
    void requestTypeGETWithUrlParameterIncludingSpaceUsingUrlEncoding() {
        Request request = Utils.parseHttpRequest("""
                GET /products?t%20e%20x%20t=M%C3%A5ste%20fixa HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.GET);
        assertThat(request.url).isEqualTo("/products");
        assertThat(request.urlParams).containsEntry("t e x t","Måste fixa");
    }

    @Test
    void requestUrlWithSpaces() {
        Request request = Utils.parseHttpRequest("""
                GET /a%20folder/first%20document.pdf HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.GET);
        assertThat(request.url).isEqualTo("/a folder/first document.pdf");
        assertThat(request.urlParams).isEmpty();
    }




    
}
