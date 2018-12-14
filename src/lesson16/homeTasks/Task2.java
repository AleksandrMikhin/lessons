package lesson16.homeTasks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//    Создать программу, которая слушает входящие подключения и выводит в консоль в виде текста входящую информацию.
//    Подключиться браузером к нему и вывести в консоль.
//    в строке браузера будет нечто вроде http://localhost:12345/


public class Task2 {

    public static void main(String[] args) {

        int port = 12345;

//        while (true){

        try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("waiting next connection: " + serverSocket + "\n");
                try (Socket socket = serverSocket.accept();
                     Scanner in = new Scanner(socket.getInputStream())) {
                    while (in.hasNext()) {
                        System.out.println(in.nextLine());
                    }
                    System.out.println("Connection close.");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }
}