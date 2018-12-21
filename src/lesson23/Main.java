package lesson23;

import chat.Message;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        java.util.concurrent.Callable
//        - возвращает данные из потока
//        java.util.concurrent.Future
//        get() - как join, только с return
//        get(long l, TimeUnit tu) - ждет таймаут. Если не дождется - выбрасывает исключение.

//        cancel() - отменяет текущую задачу
//        isCanceled()
//        isDone()

//        FutureTask
//        new FutureTask(Callable<T> c)
//        new FutureTask(Runnable r, T result)

        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "String";
            }
        });

        new Thread(task).start();
//        ждем завершение работы
        String result = task.get();
        System.out.println(result);


        FutureTask<String> task1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int counter = 0;

                while (!Thread.currentThread().isInterrupted()
                        && counter < 3)
                {
                    counter++;
                    Thread.sleep(1000);
                }

                return "Finished";
            }
        });

        new Thread(task1).start();
        Thread.sleep(2000);

        //прерываем выполнения
        task1.cancel(true);

        System.out.println(task1.isCancelled());
        System.out.println(task1.isDone());

//        Список задач
//        FutureTask futureTask = new FutureTask();

//        interface Executor позволяют управлять группой потоков
//        - создание, использование повторно
//        в Executor передается поток
//        execute(Runnable runnable)

        new Thread(task).start();

//        ExecutorService executorService; //создание объекта
//        executorService.execute(new Thread(task)); // - запускает поток
//        executorService.execute(new Thread(task1));
//        executorService.submit(new Thread(task));

//        invokeAny() -
//        shutdown -
//        submit


//        класс Executors
//        Executor.newFixedThreadPool(int i);  создание потоков
//        Executors.newSingleThreadExecutor();
//        Executors.newCachedThreadPool();
//        Executors.newScheduledThreadPool(int i);


        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("pool:" + Thread.currentThread().getName());
                }
            });

        }
        pool.shutdown(); //ждет завершения всех задач, новые не принимает

        ExecutorService service = new ThreadPoolExecutor(
                1,  //основное количество потоков
                10,  // максимальное количество потоков
                // как долго избыточные потоки будут простаивать
                2,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>() // очередь для задач
        );

//        for (int i = 0; i < 3; i++) {
//            service.submit(new Runnable() {
//                @Override
//                public void run() {
//                    // код
//                }
//            });
//        }

//        service.submit() - добавление задачи


//        объекты синхронизации

//        CountDownLatch(int num) num - счетчик событий, которые должны произойти
//        await();     - блокировка потоков
//        countDown(); - уменьшаем счетчик
//        как только счетчик обнуляется, потоки разблокируются. Используется для выравнивания потоков

//        Exchanger
//        exchange(V buffer);
//
//        Exchanger<Message> exchanger;
//        messages[4] = exchanger.exchange(messages[4]);

//     ДЗ
//        создать классы:
//        User {id, name}
//        Account {id, idUser, countMoney}
//        БанкДанных {Список аккаунтов}
//           + методПереводаДенег(откуда, куда, сколько)
//             откуда!=куда + достаточноСредств
//
//        Транзакции {    - поток в Транзакции
//            date,
//            bank,
//            amount
//            откуда
//            куда
//        } + 1 из 2х


    }
}
