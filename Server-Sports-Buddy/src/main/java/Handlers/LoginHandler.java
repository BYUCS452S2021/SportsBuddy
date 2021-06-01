package Handlers;

import EncoderDecoder.Decoder;
import EncoderDecoder.Encoder;
import Requests.LoginRequest;
import Results.LoginResult;
import Services.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.Collections;

public class LoginHandler extends BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        LoginResult result = new LoginResult();
        try {
            if (isPostRequest(exchange.getRequestMethod())) {
                LoginService service = new LoginService();

                Reader reader = new InputStreamReader(exchange.getRequestBody());
                LoginRequest reqBody = Decoder.decodeLoginRequest(reader);

                result = service.login(reqBody);

                String str = "";
                if (result.isSuccess()) {

                    str = Encoder.encodeObject(result);
                    exchange.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:4200"));
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
                    exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:4200"));
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    str = "{\"message\" : \"" + result.getMessage() + "\"}";
                }

                OutputStream respBody = exchange.getResponseBody();
                writeString(str, respBody);
                respBody.close();
            }
        } catch (IOException e) {
            try {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
                String str = new String("Error with login request");
                OutputStream respBody = exchange.getResponseBody();
                writeString(str, respBody);
                respBody.close();
            } catch (IOException b) {
                System.out.println("There is a problem");
            }
        }
    }
}
