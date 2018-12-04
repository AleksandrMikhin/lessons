package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

//Сервер принимает сообщение от клиента и отправляем ему сообщение
//  - объект класса Message, где sender="Server",
//  текст сообщения может быть любыми.


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

            Message inMessage;
            while (true) {
                if ((inMessage = ioConnection.receive()) != null) {
                   ConsoleHelper.writeString(inMessage + "\n");
                   ioConnection.send(new Message("Server", new Date().toString()));
                }
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
