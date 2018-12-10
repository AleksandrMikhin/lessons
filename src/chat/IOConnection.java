package chat;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

//IOConnection – класс соединения между клиентом и сервером
//    Класс Connection должен быть оберткой над классом java.net.Socket, которая должна
//    будет уметь сериализовать и десериализовать объекты типа Message в сокет.

public class IOConnection implements Closeable {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    @Override
    public void close() throws IOException {    // для автозакрытия в try
        in.close();                             // классу implements Closeable
        out.close();                            // + метод close()
    }

    public IOConnection(Socket socket){
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void send(Message message) {

        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Message receive() {
        Message message = null;

        try {
            message = (Message) in.readObject();
        } catch (EOFException e){
            System.out.println("Соединение разорвано. Печаль(:");
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
        }
        return message;
    }

        public SocketAddress getRemoteSocketAddress() {
            return socket.getRemoteSocketAddress();
    }
}
