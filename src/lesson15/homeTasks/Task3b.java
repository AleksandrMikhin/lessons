package lesson15.homeTasks;

//    b) Зашифровать/ дешифровать файл другим файлом

import java.io.*;

public class Task3b {

    public static void main(String[] args) throws IOException {

        File file = new File("file.txt");
        File filePass = new File("fileId.txt");
        if ((!filePass.exists()) || (filePass.length() == 0)) {
            System.out.println("Файл шифровальщик отсутствует или пуст!");
            return;
        }

        File fileOut = new File("file2.txt");
        if (!fileOut.exists()) fileOut.createNewFile();

        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
             InputStream inPass = new BufferedInputStream(new FileInputStream(filePass));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(fileOut))) {

            int read, readPass;
            byte[] b = new byte[32];
            byte[] pass = new byte[32];

            while ((read = in.read(b)) > 0) {

                readPass = inPass.read(pass);
                while (readPass < read) {
                    inPass.reset();
                    readPass += inPass.read(pass, readPass, read - readPass);
                }

                for (int i = 0; i < read; i++) {
                    b[i] ^= pass[i];
                }
                out.write(b);
            }
            out.flush();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }

    }


}
