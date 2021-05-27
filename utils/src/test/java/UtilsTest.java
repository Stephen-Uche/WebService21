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
    void requestWithOneUrlParameter() {
        Request request = Utils.parseHttpRequest("""
                POST /products?id=23 HTTP/1.1\r\n \
                Host: www.example.com\r\n \
                \r\n \
                """);
        assertThat(request.type).isEqualTo(HTTPType.POST);
        assertThat(request.url).isEqualTo("/products");
        assertThat(request.urlParams).containsEntry("id","23");
    }



    
}
