package chat;


import java.io.IOException;
import java.net.Socket;

public class Client {

    IOConnection ioConnection;

    public Client(IOConnection ioConnection) {
        this.ioConnection = ioConnection;
    }

    public void start() throws IOException {
        ConsoleHelper.writeString("Enter your name: ");
        String name = ConsoleHelper.readString();

        Thread reader = new Thread(String.valueOf(new Reader(ioConnection)));

        reader.start();

        System.out.println("Enter message to send: ");

        while (true) {
            String msg = ConsoleHelper.readString();

            if (msg != null && !msg.isEmpty()) {

                // реализовать возможность выхода по команде и смены имени
                if (msg.charAt(0) == '\\' && msg.length() > 4){
                    if (msg.substring(1, 5).equals("exit")) return;
                    else if (msg.substring(1, 5).equals("name"))
                            name = msg.substring(msg.indexOf(' ') + 1);
                } else {
                    Message message = new Message(name, msg);
                    ioConnection.send(message);
                }
            }
        }
    }

    private class Reader implements Runnable {
        private final IOConnection connection;

        public Reader(IOConnection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Message message = connection.receive();
                    ConsoleHelper.writeString(message.toString() + "\n");
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(
                new IOConnection(new Socket("127.0.0.1", 8080))
        );
        client.start();
    }
}
