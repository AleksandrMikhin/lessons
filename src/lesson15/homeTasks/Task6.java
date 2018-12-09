package lesson15.homeTasks;

//    Считать с консоли три имени файла: file1, file2, file3.
//    Разделить file1 по следующему критерию:
//    Первую половину байт записать в file2, вторую половину байт записать в file3.
//    Если в file1 количество байт нечетное, то file2 должен содержать большую часть.

import java.io.*;
import java.util.Arrays;

public class Task6 {

    public static void main(String[] args) throws IOException {

        String fileName1 = "file.txt", fileName2 = "file2.txt", fileName3 = "file3.txt";

        File file1 = new File(fileName1);
        long center = file1.length()/2 + (file1.length() % 2);

        File file2 = new File(fileName2);
        File file3 = new File(fileName3);
        file2.createNewFile();
        file3.createNewFile();

        byte[] b = new byte[32];

        try (InputStream in = new BufferedInputStream(new FileInputStream(file1));
                OutputStream out1 = new BufferedOutputStream(new FileOutputStream(file2));
                OutputStream out2 = new BufferedOutputStream(new FileOutputStream(file3))) {

            int read;
            long readCount = 0;

            while ((read = in.read(b)) > 0) {
                readCount += read;

                if (readCount > center) {
                    if ((center + 32 - readCount) > 0) {
                        out1.write(Arrays.copyOfRange(b, 0,b.length -(int)(readCount - center)));
                        out2.write(Arrays.copyOfRange(b, b.length - (int)(readCount - center), (int)(readCount - center)));
                    }
                    else out2.write(b);
                }
                else out1.write(b);
            }
        }
    }
}
