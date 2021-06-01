package Server;

import Handlers.DefaultHandler;
import Handlers.LoginHandler;
import Handlers.RegisterHandler;
import Handlers.UserHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    private void run(String portNumber) {

        System.out.println("Initializing HTTP Server");

        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");

        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
//        server.createContext("/clear", new ClearHandler());
//        server.createContext("/fill", new FillHandler());
//        server.createContext("/load", new LoadHandler());
        server.createContext("/user", new UserHandler());
//        server.createContext("/event", new EventHandler());
        server.createContext("/", new DefaultHandler());

        System.out.println("Starting server");

        server.start();

        System.out.println("Server Started");
    }

    public static void main(String[] args) {
        String portNumber = "1010";//args[0];
        new Server().run(portNumber);
    }
}
