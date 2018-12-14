package lesson16.homeTasks.task1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {

    private void start(int port) {

        System.out.print("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        try (Socket socket = new Socket("localhost", port)) {

            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                out.writeObject(new Command(name, "/connect"));
                out.flush();

                String mess;
                while (true) {
                    System.out.print(name + "> ");
                    mess = scanner.nextLine().trim();

                    switch (mess) {
                        case "/exit": {
                            out.writeObject(new Command(name, mess));
                            out.flush();
                            return;
                        }

                        case "/ping": {
                            Date date = new Date();
                            out.writeObject(new Command(name, mess));
                            out.flush();

                            in.readObject();
                            System.out.println("ping reply " + (new Date().getTime() - date.getTime()) + "ms.");
                            break;
                        }
                        case "/list_users":
                        case "/server_time": {
                            out.writeObject(new Command(name, mess));
                            out.flush();
                            Message message = (Message) in.readObject();
                            System.out.println(message);
                            break;
                        }
                        default:
                            out.writeObject(new Message(name, mess));
                            out.flush();
                    }
                }

            } catch (IOException e) {
                System.out.println("no connect");
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.start(8080);
    }
}