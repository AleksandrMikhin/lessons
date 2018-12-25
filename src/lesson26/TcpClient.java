package lesson26;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpClient extends Thread{
    @Override
    public void run() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 1234));

            OutputStream out = socket.getOutputStream();
            out.write(125);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TcpServer extends Thread {
    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(1234)) {
            try (Socket socket = serverSocket.accept()){

                InputStream in = socket.getInputStream();
                byte data = (byte) in.read();

                System.out.println(data);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}