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
            e.printStackTrace();
        }
    }

    public void send(Message message) throws IOException {

            out.writeObject(message);
            out.flush();
    }


    public Message receive() throws IOException, ClassNotFoundException {

        return (Message) in.readObject();
    }

        public SocketAddress getRemoteSocketAddress() {
            return socket.getRemoteSocketAddress();
    }
}
