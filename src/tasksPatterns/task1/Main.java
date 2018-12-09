package tasksPatterns.task1;

//    Используя паттерн Decorator создать шифрующие потоки ввода/вывода с помощью XOR (можете выбрать другой способ).
//    Они должны наследовать FilterInputStream и FilterOutputStream.
//    Конструктор этих потоков должен принимать пароль в виде массива байт и поток, который он декорирует.
//
//    Использование должно выглядеть следующим образом:
//    Трафик автоматически шифрует и дешифруется:
//    InputStream in = new CryptoInputStream(socket.getInputStream(), password);
//    OutputStream out = new CryptoOutputStream(socket.getOutputStream(), password);
//
//    Сохраняем в шифрованный файл и читаем из шифрованного файла:
//    InputStream in = new CryptoInputStream(new FileInputStream("test.bin"), password);
//    OutputStream out = new CryptoOutputStream(new FileOutputStream("test.bin"), password);
//
//    Для простоты можете начать имплементацию, где пароль - один байт.

import java.io.*;

public class Main {

    public static void main(String[] args) {

        String fileName = "file.txt";
        String fileName2 = "file2.txt";

        String password = "MegaPass:)";

        try {
            InputStream in = new CryptoInputStream(new FileInputStream(fileName), password);
            OutputStream out = new CryptoOutputStream(new FileOutputStream(fileName2), password);

            byte[] buffer = new byte[32];

            int readByteCount;
            while ((readByteCount = in.read(buffer)) > 0) {
                out.write(buffer, 0, readByteCount);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("Произведен XOR: " + fileName + " -> " + fileName2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
