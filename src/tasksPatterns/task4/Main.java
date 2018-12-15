package tasksPatterns.task4;

//    Самостоятельно изучить паттерн Стратегия. Реализовать Logger.
//
//    1. Интерфейс ILogger с мотодом write()
//    2. Реализовать три стратегии:
//
//    ConsoleLogger - выводит сообщения в консоль
//    FileLogger - записывает сообщение в файл
//    TimeFileLogger - записывает сообщение в файл + текущее время
//
//    3. Реализовать классы, исползующие эти стратегии.
//    Запись в файл должна работать!

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Logger log = new Logger(new ConsoleLogger());
        log.write("Лог в консоль");

        FileLogger fileLogger = new FileLogger();
        log.setStrategy(fileLogger);
        log.write("Что-нибудь в лог");

        TimeFileLogger timeFileLogger = new TimeFileLogger();
        log.setStrategy(timeFileLogger);
        log.write("Что-нибудь в лог со временем");
    }

}


class Logger{
    private ILogger logger;

    public Logger(ILogger logger) {
        this.logger = logger;
    }

    public void setStrategy(ILogger logger) {
        this.logger = logger;
    }

    public void write(String message) {
            logger.write(message);
    }
}

interface ILogger {
    void write(String str);

    default void addToFile(String fileName, String strMessage) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) file.createNewFile();
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(strMessage + "\n");
            fileWriter.flush();
        }
    }
}

class ConsoleLogger implements ILogger {

    @Override
    public void write(String str) {
        System.out.println(str);
    }
}

class FileLogger implements ILogger {

    final String fileName = "file.txt";

    @Override
    public void write(String str) {
        try {
            addToFile(fileName, str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TimeFileLogger implements ILogger {

    final String fileName = "timeFile.txt";

    @Override
    public void write(String str) {
        try {
            addToFile(fileName, new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) + " : " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
