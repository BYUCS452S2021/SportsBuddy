package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultHandler extends BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        final OutputStream responseBody = exchange.getResponseBody();
        try {
            if (isGetRequest(exchange.getRequestMethod())) {
                String reqUrl = exchange.getRequestURI().toString();

                String urlPath = "";
                if(reqUrl.equals("/")) {
                    urlPath = "web/index.html";
                }
                else {
                    urlPath = "web" + reqUrl;
                }

                Path filePath = FileSystems.getDefault().getPath(urlPath);
                if (Files.exists(filePath)) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    Files.copy(filePath, responseBody);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    Path notFoundPath = FileSystems.getDefault().getPath("web/HTML/404.html");
                    Files.copy(notFoundPath, responseBody);
                }
                responseBody.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            responseBody.close();
        }
    }
}
