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
                         ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
                    {
                        boolean flag = true;
                        while (flag) {

                            Object obj = in.readObject();
                            if (obj instanceof Command) {

                                Command command = (Command) obj;
                                String currentUser = command.getSender();

                                switch (command.getCommand()) {
                                    case "/connect": {
                                        if (!listUsers.contains(currentUser)) listUsers.add(currentUser);
                                        System.out.println(currentUser + " connected");
                                        break;
                                    }

                                    case "/exit": {
                                        flag = false;
                                        break;
                                    }

                                    case "/list_users": {
                                        out.writeObject(new Message("server", Arrays.toString(listUsers.toArray())));
                                        out.flush();
                                        break;
                                    }
                                    case "/ping": {
                                        out.writeObject(new Message("server", "/pingReply"));
                                        out.flush();
                                        break;
                                    }
                                    case "/server_time": {
                                        out.writeObject(new Message("server", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").
                                                format(new Date())));
                                        out.flush();
                                        break;
                                    }
                                }

                            } else {
                                Message message = (Message) obj;
                                System.out.println(message.toString() + "\n");
                            }
                        }
                        System.out.println("user has disconnected");

                    } catch (IOException e) {
                        System.out.println("user has disconnected");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
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
