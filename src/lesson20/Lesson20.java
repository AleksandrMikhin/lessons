package lesson20;

public class Lesson20 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

//        currentThread();
//        setName();
//        getName();
//        join();
//        isAlive();
//        isInterrupted();
//        interrupt();

        Thread thread = new Thread();   // создание потока
        thread.start();                 // старт потока

        Thread thread1 = new MyThread();
        thread1.start();

        Thread thread2 = new Thread(new OtherThread());
        thread2.start();

        for (int i = 0; i < 5; i++) {
            Thread thread3 = new Thread(new OtherThread());
            thread3.setName("T-" + i);
            thread3.start();
        }

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running isAlive - " + Thread.currentThread().isAlive());
                System.out.println("Running getState - " + Thread.currentThread().isAlive());
            }

        });

        System.out.println("Before start isAlive - " + thread4.isAlive());
        System.out.println("Before start getState - " + thread4.isAlive());
        thread4.start();

        // ожидаем, пока поток завершится
        thread4.join();
        System.out.println("After start isAlive - " + thread4.isAlive());
        System.out.println("Afret start getState - " + thread4.isAlive());


//        Thread thread5 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {                        //бесконечный цикл в Основном потоке
//                    try {                             //программа не завершится никогда
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread5.start();

        Thread thread6 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread6.setDaemon(true);
        thread6.start();

//        Программа будет работать, пока есть незавершенные потоки
//        Это касается ОСНОВНЫХ потоков
//        Потоки, отмеченные как daemon - .setDaemon(true) - становятся не основными

//        Остановка потоков:
//          поток выполнил все инструкции и вышел из метода run
//          в методе run было выброшено необрабатываемое исключение
//          остановилась JVM
//          основной поток завершил свою работу.


        Thread thread7 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("thread7");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //Thread.currentThread().interrupt();
                        System.out.println("InterrupterException");
//                        e.printStackTrace();
                    }
                }
            }
        });
        thread7.start();
        thread.sleep(2000);
        System.out.println(thread7.isInterrupted());
        thread7.interrupt();
    }
}

// для создания своего потока нужно унаследовать класс Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread " +
                Thread.currentThread().getName());
    }
}

// либо 2й вариант - реализовать метод Runnable. Обычно используют этот метод, чтобы освободить extends
class  OtherThread implements Runnable {
    @Override
    public void run() {
        System.out.println("OtherThread " +
            Thread.currentThread().getName());
    }
}