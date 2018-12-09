package tasksFiles;

//    CrUD (создание, чтение, редактирование, удаление) для таблицы внутри файла.
//    Имя файла для операций CrUD считывать с консоли.
//
//    Программа запускается с одним из следующих наборов параметров:
//
//    -u id productName price quantity
//    -d id
//    -c productName price quantity
//
//    Значения параметров:
//    -u  - обновляет данные товара с заданным id
//    -d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)
//    -c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле
//
//    id - 8 символов
//    productName - название товара, 30 chars (60 bytes)
//    price - цена, 8 символов
//    quantity - количество, 4 символа
//
//    В файле данные о каждом товаре записываются с новой строки  в следующей последовательности (с разделяющими пробелами или без них - на Ваш выбор):
//
//    id productName price quantity
//
//    Пример:
//    12345 Барабанные палочки 159.00 12

import java.io.*;
import java.util.Arrays;

public class Task4 {

    private File file;

    public Task4(String string) throws IOException {
        this.file = new File(string);
        if (!file.exists()) file.createNewFile();
    }

    public int create(String name, double price, long quantity) throws IOException {

        int maxId = 0;
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))){
            byte[] b = new byte[81];
            while (in.read(b) > 0) {
                int readId = Integer.parseInt(new String(Arrays.copyOfRange(b, 0, 8)));
                if (maxId < readId) maxId = readId;
            }
        }
        maxId ++;

        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
            String s = String.format("%08d", maxId) +
                    new String(String.format("%30s", name).substring(0, 30).getBytes("UTF-16BE")) +
                    String.format("%8.2f", price) +
                    String.format("%04d", quantity) +
                    "\n";

          fileOutputStream.write(s.getBytes());
        }
        return maxId;
    }

    public boolean update(int id, String name, double price, long quantity) throws IOException {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            byte[] b = new byte[8];
            while (randomAccessFile.read(b) > 0){
                if (Integer.parseInt(new String(b)) == id)
                {
                    String s = new String(String.format("%30s", name).substring(0, 30).getBytes("UTF-16BE")) +
                                String.format("%8.2f", price) +
                                String.format("%04d", quantity) +
                                "\n";

                    randomAccessFile.write(s.getBytes());
                    return true;
                }
                randomAccessFile.seek(randomAccessFile.getFilePointer()+73);
            }
        }
        return false;
    }

    public boolean delete(int id) throws IOException {

        try (InputStream in = new BufferedInputStream(new FileInputStream(file))){
            int findPos = 0;
            byte[] b = new byte[81];
            while (in.read(b) > 0) {
                if (id == Integer.parseInt(new String(Arrays.copyOfRange(b, 0, 8)))) {

                    File tempFile = new File(file.getName() + ".temp");
                    tempFile.createNewFile();

                    int copyPos = 0;
                    try (InputStream inCopy = new BufferedInputStream(new FileInputStream(file));
                         OutputStream outCopy = new BufferedOutputStream(new FileOutputStream(tempFile))) {
                        while (inCopy.read(b) > 0) {
                            if (copyPos != findPos) outCopy.write(b);
                            copyPos++;
                        }
                    }

                    in.close();
                    file.delete();
                    tempFile.renameTo(file);
                    return true;
                }
                findPos++;
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {

//        args = new String[]{"-c Печенька 25.10 146"};
//        args = new String[]{"-u 3 Шоколадка 124.60 446"};
//        args = new String[]{"-d 2"};

        Task4 task = new Task4("fileId.txt");

        try {
            String[] strArg = args[0].split("\\s");

            if ((strArg.length < 2) || (strArg[0].length() < 2))
                throw new NumberFormatException();  //наверное, так далать не хорошо? :)
            switch (strArg[0].charAt(1)) {
                case 'c':
                    System.out.println("Товар добавлен с id " + task.create(strArg[1], Double.parseDouble(strArg[2]), Integer.parseInt(strArg[3])));
                    break;
                case 'd':
                    System.out.println("Товар с id " + Integer.parseInt(strArg[1]) +
                            (task.delete(Integer.parseInt(strArg[1])) ? " удален." : " не найден!"));
                    break;
                case 'u':
                    System.out.println("Товар с id " + Integer.parseInt(strArg[1]) +
                            (task.update(Integer.parseInt(strArg[1]), strArg[2], Double.parseDouble(strArg[3]), Integer.parseInt(strArg[4])) ? " изменен." : " не найден!"));
                    break;
                default:
                    System.out.println("Команда не распознана!");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Команда не распознана!");
            e.getStackTrace();
        }
    }

}
