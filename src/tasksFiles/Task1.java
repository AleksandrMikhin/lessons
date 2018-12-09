package tasksFiles;

//    Написать программу, которая удаляет из файла все слова, содержащие заданное
//    количество символов (количество символов задается диапазоном, например 4-6).
//    Для вывода результатов создавать новую директорию и файл средствами класса File

import java.io.*;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        int lengthMin = 4;
        int lengthMax = 6;

        String fileName = "file.txt";
        String newDir = "toFileTxtDir";

        File fileOut = new File(newDir);
        fileOut.mkdir();
        fileOut = new File(newDir, fileName);

        try (Scanner in = new Scanner(new File(fileName));
             OutputStream out = new FileOutputStream(fileOut)) {

            while(in.hasNext()){
                String str = in.nextLine();
                out.write((str.replaceAll("\\b[A-Za-zА-Яа-яЁё]{" + lengthMin + "," + lengthMax + "}\\b", "") + "\n").getBytes() );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
