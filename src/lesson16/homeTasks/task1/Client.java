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

                Message message = new Message(name, "/connect");
                message.writeExternal(out);
                out.flush();

                String mess;
                while (true) {
                    System.out.print(name + "> ");
                    mess = scanner.nextLine();
                    message.setMessText(mess);

                    switch (mess) {
                        case "/exit": return;
                        case "/ping": {
                            Date date = new Date();
                            message.writeExternal(out);
                            out.flush();
                            message.readExternal(in);
                            System.out.println("ping reply " + (new Date().getTime() - date.getTime()) + "ms.");
                            break;
                        }
                        case "/list_users":
                        case "/server_time": {
                            message.writeExternal(out);
                            out.flush();
                            message.readExternal(in);
                            System.out.println(message);
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("no connect");
                return;
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.start(8080);
    }
}