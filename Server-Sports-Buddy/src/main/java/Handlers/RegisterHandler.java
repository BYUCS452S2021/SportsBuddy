package Handlers;

import EncoderDecoder.Decoder;
import EncoderDecoder.Encoder;
import Requests.RegisterRequest;
import Results.RegisterResult;
import Services.RegisterService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.Collections;

public class RegisterHandler extends BaseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (isPostRequest(exchange.getRequestMethod())) {
                RegisterService r = new RegisterService();
                Reader reader = new InputStreamReader(exchange.getRequestBody());
                RegisterRequest req = Decoder.decodeRegisterRequest(reader);
                RegisterResult result = r.register(req);

                if (result.isSuccess()) {
                    exchange.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:4200"));
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    String str = Encoder.encodeObject(result);
                    OutputStream respBody = exchange.getResponseBody();
                    writeString(str, respBody);
                    respBody.close();
                }
                else {
                    exchange.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:4200"));
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    String str = "{\"message\" : \"" + result.getMessage() + "\"}";
                    OutputStream respBody = exchange.getResponseBody();
                    writeString(str, respBody);
                    respBody.close();
                }
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR,0);
            String str = "Internal server error";
            writeString(str, exchange.getResponseBody());
            exchange.getRequestBody().close();
        }
    }

}
