package lesson15.homeTasks;

//    Ввести с консоли имя файла.
//    Считать все байты из файла.
//    Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task4 {

    public static void main(String[] args) throws IOException {

//        System.out.print("Введите имя файла: ");
//        File file = new File((new Scanner(System.in)).nextLine());

        File file = new File("file.txt");
        Set<Byte> set = new HashSet();

        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {

            int read;
            byte[] b = new byte[32];

            while ((read = in.read(b)) > 0 && set.size() < 256)
                for (int i = 0; i < read; i++)
                    if (!set.contains(new Byte(b[i]))) set.add(new Byte(b[i]));

            byte[] array = new byte[set.size()];
            int i = 0;
            for (Byte element : set){
                array[i] = element.byteValue();
                i++;
            }

            Arrays.sort(array);
            System.out.println(Arrays.toString(array));

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

}
