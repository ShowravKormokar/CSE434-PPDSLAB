package http_handller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

// This class implements the HttpHandler interface to handle HTTP requests for the "/showrav" context. It responds with a simple "Hello world!" message.
class HelloHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, 0);
        OutputStream od = exchange.getResponseBody();
        String response = "Hello, Showrav.";
        od.write(response.getBytes());
        od.close();
        exchange.close();

    }
}

// This class handles HTTP requests to the "/html" context and responds with an
// HTML page containing a greeting message and a marquee effect.
class Html implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, 0);
        OutputStream od = exchange.getResponseBody();
        String response = """
                <html>
                <body>Hello world! <marquee>I am Showrav.<marquee>
                </body>
                </html>
                """;
        od.write(response.getBytes());
        od.close();
        exchange.close();

    }
}

// This class sets up an HTTP server that listens on port 8080 and defines two
// contexts: "/showrav" and "/html".
public class HTTP_Handler {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/showrav", new HelloHandler());
        server.createContext("/html", new Html());

        server.start();
        System.out.println("Server started and listening on port 8080. URL: http://localhost:8080/html");
    }
}