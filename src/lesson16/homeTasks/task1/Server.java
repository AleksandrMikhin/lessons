package lesson16.homeTasks.task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

//Сделать команды (консоль) для сервера. Команды должны реализовывать интерфейс Externalizable:
//    1. /list_users - сервер возвращает список всех пользователей, которые заходили на сервер.
//    2. /server_time - возвращает время сервера.
//    3. /ping - измеряет время сообщения клиент-сервер.

public class Server {

    Set<String> listUsers = new HashSet<>();

    public void start(int port) {

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server started: " + serverSocket);

                while (true) {
                    try (Socket socket = serverSocket.accept();
                         ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                        Message message = new Message();
                        message.readExternal(in);
                        if (!message.getMessText().equals("/connect")) break;
                        String currentUser = message.getSender();

                        if (!listUsers.contains(currentUser)) listUsers.add(currentUser);

                        while (!message.getMessText().trim().equals("/exit")) {

                            switch (message.getMessText().trim()) {
                                case "/connect": {
                                    System.out.println(currentUser + " connected");
                                    break;
                                }
                                case "/list_users": {
                                    new Message("server", Arrays.toString(listUsers.toArray())).writeExternal(out);
                                    out.flush();
                                    break;
                                }
                                case "/ping": {
                                    new Message("server", "/pingReply").writeExternal(out);
                                    out.flush();
                                    break;
                                }
                                case "/server_time": {
                                    new Message("server", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").
                                            format(new Date())).writeExternal(out);
                                    out.flush();
                                    break;
                                }
                                default: {                                              //не понятно - почему сюда не заходит, если нет совпадений?
                                    System.out.println(message.toString() + "\n");
                                    break;
                                }
                            }
                            message.readExternal(in);
                        }
                    } catch (IOException e) {
//                            e.printStackTrace();
                        System.out.println("user has disconnected");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(8080);
    }

}
