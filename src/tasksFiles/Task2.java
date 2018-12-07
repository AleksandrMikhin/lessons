package tasksFiles;

//    Написать программу, которая создает текстовый файл и записывает в него список файлов из заданного каталога.
//    Программа запускается с параметром: имя каталога.
//
//    Информацию по каждому файлу записываеть с новой строки, которая содержит:
//        путь к файлу,
//        имя файла,
//        дата создания файла


import java.io.*;
import java.util.Date;

public class Task2 {

    public static void main(String[] args) {

//        String path = args[0];
        String path = "c:\\";

        File file = new File(path);
        String[] filesList = file.list();

        try (OutputStream out = new FileOutputStream(new File("file.info"))){
            for (String fileName: filesList) {
                file = new File(path, fileName);
                out.write(
                        (path + " | " + fileName + " | " + new Date(file.lastModified()) + "\n").getBytes()
                );
                }
            out.flush();

            } catch (IOException e1) {
               e1.printStackTrace();
        }
    }

}
