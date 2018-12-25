package lesson26;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpServer extends Thread {
    @Override
    public void run() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(1234);
            byte[] buf = new byte[1];

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(packet);

            System.out.println(buf[0] + " " + packet.getLength());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class UdpClient extends Thread {
    @Override
    public void run() {
        DatagramPacket packet = new DatagramPacket(new byte[]{10}, 1,
                new InetSocketAddress("localhost", 1234));

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}