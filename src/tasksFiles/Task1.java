package tasksFiles;

//    Написать программу, которая удаляет из файла все слова, содержащие заданное
//    количество символов (количество символов задается диапазоном, например 4-6).
//    Для вывода результатов создавать новую директорию и файл средствами класса File

import java.io.*;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        File file = new File("file.txt");
        File file2 = new File("file2.txt");
        int lengthDel = 4;

        try (Scanner in = new Scanner(file);
             OutputStream out = new FileOutputStream(file2)) {

            while(in.hasNext()){
                String[] arr = in.nextLine().split(" ");

                StringBuilder tempStr = new StringBuilder();
                for (String s: arr) {
                    if (s.length() != lengthDel) tempStr.append(s).append(" ");
                }
                if (tempStr.length() > 0) out.write( tempStr.append("\n").toString().getBytes() );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

    }



}
