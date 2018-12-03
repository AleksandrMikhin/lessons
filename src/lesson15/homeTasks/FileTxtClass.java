package lesson15.homeTasks;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


//  Попробуйте создать класс для работы только с txt файлами.

public class FileTxtClass extends RandomAccessFile {

    private String fileName;

    public FileTxtClass(String fileName, String mode) throws FileNotFoundException, FileNotTxtException {
        super(fileName, mode);

        if (fileName.trim().substring(fileName.length()-4, 4).toLowerCase().equals(".txt")) {
            this.fileName = fileName;
        }else {
            throw new FileNotTxtException("Файл не txt-типа!");
        }

    }

}



