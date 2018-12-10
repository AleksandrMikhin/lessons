package chat;


import java.io.IOException;
import java.net.Socket;

public class Client2 {

    IOConnection ioConnection;

    public Client2(IOConnection ioConnection) {
        this.ioConnection = ioConnection;
    }

    public void start() {
        ConsoleHelper.writeString("Enter your name: ");
        String name = ConsoleHelper.readString();

        Thread reader = new Thread(String.valueOf(new Reader(ioConnection)));

        reader.start();


    }

    private class Reader implements Runnable {
        private final IOConnection connection;

        public Reader(IOConnection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = connection.receive();
                System.out.println(message);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Client2 client = new Client2(
                new IOConnection(new Socket("127.0.0.1", 8080))
        );
    }
}
