package lesson15.homeTasks;

//    Скопировать файл (имена файлов задаются с клавиатуры).
//    Программа должна выводить количество скопированных байт.

import java.io.*;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        File file = new File(scanner.nextLine());
        System.out.print("Введите имя для копии: ");
        File fileCopy = new File(scanner.nextLine());

//        File file = new File("file.txt");
//        File fileCopy = new File("fileCopy.txt");

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(fileCopy));

            byte[] buffer = new byte[16];
            int readByteCount, readLength = 0;
            while ((readByteCount = in.read(buffer)) > 0) {
                readLength += readByteCount;
                out.write(buffer, 0, readByteCount);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("Скопировано байт: " + readLength);
        } catch (FileNotFoundException e) {
            System.out.println(file.getName() + " - не найден.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
