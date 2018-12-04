package chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private void start() {
        ConsoleHelper.writeString("Enter your name: ");
        String name = ConsoleHelper.readString();

        try (Socket socket = new Socket("localhost", 8080)) {
            IOConnection ioConnection = new IOConnection(socket);
            Message inMessage;

            while (true) {
                ConsoleHelper.writeString(name + " > ");
                String str = ConsoleHelper.readString();
                if (str.equals("q")) break;

                ioConnection.send(new Message(name, str));
                if ((inMessage = ioConnection.receive()) != null) {
                    ConsoleHelper.writeString(inMessage + "\n");
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
     }
}
