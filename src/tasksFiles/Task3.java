package tasksFiles;

//    Написать программу, которая копирует файлы
//    из одного каталога в другой (имена каталогов задаются при запуске программы)

import java.io.*;

public class Task3 {

    public static void main(String[] args) {

        String copyFrom = "c:\\1\\";
        String copyTo = "c:\\2\\";

        File file = new File(copyFrom);
        String[] filesList = file.list();

        for (String copyFile: filesList) {

            file = new File(copyFrom, copyFile);
            if (file.isFile()) {
                try {
                    InputStream in = new FileInputStream(file);
                    OutputStream out = new FileOutputStream(new File(copyTo, copyFile));

                    byte[] buffer = new byte[32];

                    int readByteCount;
                    while ((readByteCount = in.read(buffer)) > 0) {
                        out.write(buffer, 0, readByteCount);
                    }
                    in.close();
                    out.flush();
                    out.close();
                    System.out.println("Скопирован: " + copyFile);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
