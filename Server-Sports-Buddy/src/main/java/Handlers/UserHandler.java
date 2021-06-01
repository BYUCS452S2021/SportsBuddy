package Handlers;

import EncoderDecoder.Decoder;
import EncoderDecoder.Encoder;
import Models.User;
import Results.UserResult;
import Services.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Scanner;

public class UserHandler extends BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        UserResult r2 = new UserResult();
        try {

            if (isGetRequest(exchange.getRequestMethod())) {
                String str = "";

                String q = exchange.getRequestURI().getQuery();
                System.out.println(q);

                    String token = q;

                    String reqUrl = exchange.getRequestURI().toString();
                    StringBuilder url = new StringBuilder(reqUrl);
                    url.deleteCharAt(0);

                        UserService userService = new UserService();
                        UserResult result = userService.getUser(token);
                        System.out.println(result.isSuccess());
                        str = Encoder.encodeObject(result);
                        System.out.println(str);
                        if (result.isSuccess()) {
                            exchange.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
                            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:4200"));
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.setAttribute("Access-Control-Allow-Origin", "http://localhost:4200");
                            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("http://localhost:4200"));
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
//                    }
                    OutputStream respBody = exchange.getResponseBody();
                System.out.println(str);
                    writeString(str, respBody);
                    respBody.close();
                }
        }
        catch (IOException e) {
            try {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
                String str = "{\"message\": " + e.getLocalizedMessage() + "\"}";
                OutputStream respBody = exchange.getResponseBody();
                writeString(str, respBody);
                respBody.close();
                e.printStackTrace();
            } catch (IOException b) {
                System.out.println("There is a problem");
            }
        }
    }


}
