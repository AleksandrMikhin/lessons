package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept())
        {
            IOConnection ioConnection = new IOConnection(socket);
            ConsoleHelper.writeString("Server started on: " + serverSocket + "\n");

            while (true) {
                ConsoleHelper.writeString(ioConnection.receive() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
    }

 }
