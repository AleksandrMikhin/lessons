package lesson15.homeTasks;

//    a) Зашифровать/ дешифровать файл паролем (XOR) (посмотреть в интернете)

import java.io.*;

public class Task3a {

    public static void main(String[] args) throws IOException {

        File file = new File("file.txt");
        File fileOut = new File("file2.txt");
        if (!fileOut.exists()) fileOut.createNewFile();

        byte[] pass = "password".getBytes();

        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(fileOut))) {

            int read;
            long readCount = 0;
            byte[] b = new byte[32];

            while ((read = in.read(b)) > 0) {
                for (int i = 0; i < read; i++) {
                    b[i] ^= pass[(int) ((readCount + i) % pass.length)];
                }
                out.write(b);
                readCount += read;
            }
            out.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }

    }
}
