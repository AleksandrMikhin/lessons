package lesson21;

import java.util.ArrayList;
import java.util.List;

public class Lesson21 {

    public static void main(String[] args) throws InterruptedException {
        Sync sync = new Sync();
        List<Sync.Adder> adderList = new ArrayList<>(100);

        //создаем потоки
        for (int i = 0; i < 100; i++){
            adderList.add(sync.new Adder());
        }

        //запускаем потоки
        for (Sync.Adder adder: adderList) {
            adder.join();
        }

        //вывод результата
        System.out.println("Result: " + sync.counter);

    }

}

//общий участок памяти, который будем изменять из различных потоков

class Sync {
    int counter;

    public synchronized void increment() {  //синхронизация метода. Происходит на текущем объекте (this)
            counter++;                      //либо можно синхронизировать участок кода, как ниже
    }

    public class Adder extends Thread {
        @Override
        public void run(){
            for (int i = 0; i < 1_000; i++){
//                counter++;
                synchronized (Sync.this) {  // - объект, на котором происходит синхронизация
                    counter++;              //в этом блоке может выполняться только одним потоком
//                    increment();
                }
            }
        }
    }
}

class Storage {
    int booksCount = 0;

    public synchronized void getBook() throws InterruptedException {
        System.out.println("getBook start");
        while (booksCount < 1) {
            wait();     //блокирует поток, пока его кто-то не разбудит, но он может проснуться в любой рандомный момент
        }
        booksCount--;
        System.out.println("1 забрали, осталось" + booksCount);
        System.out.println("getBook finish");
        notify(); //разбудит случайно выбранный поток, notifyAll(); - разбудит все
    }

    public synchronized void putBook() throws InterruptedException {
        System.out.println("putBook start");
        while (booksCount >=5 ) {
            wait();
        }
        booksCount++;
        System.out.println("1 добавили, стало" + booksCount);
        System.out.println("putBook finish");

    }
}

class Put implements Runnable{

    Storage storage;

    public Put(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            try {
                storage.putBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Get implements Runnable{

    Storage storage;

    public Get(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            try {
                storage.getBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Library{

    public static void main(String[] args) {
//        Storage storage = new Storage();
//        new Thread (new Put(storage)).start();
//        new Thread (new Get(storage)).start();
//
        Object obj1 = new Object();
        Object obj2 = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 start");
                synchronized (obj1) {
                    System.out.println("Thread 1 locked obj1");
                    synchronized (obj2) {
                        System.out.println("Thread 1 locked both obj");
                    }
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 start");
                synchronized (obj2) {
                    System.out.println("Thread 2 locked obj2");
                    synchronized (obj1) {
                        System.out.println("Thread 2 locked both obj");
                    }
                }
            }
        });
    }
}


//выполнить подсчет топ100 слов парралельно
//Количество потоков должно быть равно числу доступных процессоров системы

//считать все из файла в коллекцию а потом ищем топ100 слов
//каждый поток собирает данные в свой Map (кусок файла)
//поток 1й, который создавал потоки - ждет завершения всех остальных - отображает результат
//Runtime.getRuntime().availableProcessors();
