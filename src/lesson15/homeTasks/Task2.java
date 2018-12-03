package lesson15.homeTasks;

//    Разбить/склеить файл (имена файлов и размер куска задаются с клавиатуры).

import java.io.*;

public class Task2 {

    public static int toSplit(String str, int length) {

        File file = new File(str);
        byte[] buffer = new byte[length];
        int readByteCount, part = 0;

        try {
            InputStream in = new BufferedInputStream(new FileInputStream(file));

            while ((readByteCount = in.read(buffer)) > 0) {
                File newFile = new File(str + ".part" + part);
                OutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
                out.write(buffer, 0, readByteCount);
                out.flush();
                out.close();
                part ++;
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return part --;
    }


    public static int glue(String str) {

        File newFile = new File(str);
        byte[] buffer = new byte[32];
        int readByteCount, part = 0;

        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));

            File partFile = new File(str + ".part" + part);
            while (partFile.exists()) {
                InputStream in = new BufferedInputStream(new FileInputStream(partFile));
                while ((readByteCount = in.read(buffer)) > 0) {
                    out.write(buffer, 0, readByteCount);
                }
                in.close();
                part++;
                partFile = new File(str + ".part" + part);
            }
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return part --;
    }



    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите имя файла: ");
//        String path = scanner.nextLine();
//        System.out.print("Введите размер куска: ");
//        int sizePart = scanner.nextInt();

//        System.out.println(toSplit("file.txt", 50));
        glue("file.txt");
    }
}
