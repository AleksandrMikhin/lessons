package lesson22;

import java.util.*;
import java.util.concurrent.*;

public class SomeClass {

    //volatile - значение не будет кэшировано потоками, будет работа всегда из памяти
    //             т.е. чтение и запись будут всегда атомарными
    private static volatile boolean run = true;
//    private volatile Object object = new Object();


    public static void main(String[] args) throws InterruptedException{
        for (int i = 0; i < 1000; i++){
            new SomeThread().start();
        }
        Thread.sleep(3000);
        run = false;
    }

    private static class SomeThread extends Thread {
        @Override
        public void run() {
            while (run) {
                try {
                    sleep(1);
                } catch (InterruptedException e){
                    run = false;
                    e.printStackTrace();
                }
            }
        }
    }

    // потокобезопасные многопоточные коллекции, используют синхронизацию
    // Vector
    // HashTable
    // Stack
    // StringBuffer

    // однопоточные коллекции плюс декораторы
//    List<String> list = Collections.synchronizedList(new ArrayList<>());
//    Set<String> set = Collections.synchronizedSet(new HashSet<>());
//    Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());

//  java.util.concurrent. - java 1.5

//  TimeUnit unit

//    многопоточные коллекции
//    интерфейс ConcurrentMap
//  чтение и запись будут всегда атомарными
//    операции чтения не требуют блокировки
//    операции записи могут происходить без блокировок

//  putIfAbsent(key, value) - добавит пару, если значение такой нет, если ключ найдет - перепишет значение
//  remove(key, value)
//  replace(key, oldValue, newValue) - замена если старое значение соответствует, на повое

//  реализация
//  ConcurrentHashMap - блокировки на уровне сегментаов (делит его на несколько сегментов)
//  ConcurrentSkipListMap (Skip List алгоритм)

//  ConcurrentSkipListSet (Skip List алгоритм)

//  CopyOnWriteArrayList  (много читателей, писателей мало)
//  CopyOnWriteArraySet   (создается копия списка для чтения, пока идет запись)

//  блокирующие очереди
//  интерфейс BlockingQueue - заблокирует читателей, если очередь пуста и писателей если полная (когда стоит ограничение размера)
//  интерфейс BlockingDeque - двунаправленная
//  вставка
//  add() - Exception, если некуда вставлять данные
//  offer() - boolean
//  put() - добавляет, если некуда - заснет, будет разбужен, когда появится куда

//  удаление
//  remove() - Exception
//  poll() -
//  take() - блокирует поток, пока не появится чего удалять

//  реализация
//  ArrayBlockingQueue (размер фиксирован)
//    new ArrayBlockingQueue(int capacity);
//    new ArrayBlockingQueue(int capacity, boolean fair); - создает очередь для заблокированных потоков, которые ждут записи/чтения
//    new ArrayBlockingQueue(int capacity, boolean fair, Collection collection); - на основе коллекции
//
//    LinkedBlockingQueue() - если размер не указат, то Integer.MAX_VALUE
//    LinkedBlockingQueue(int capacity)
//    LinkedBlockingQueue(Collection collection)
//
//    SynchronousQueue - каждая операция добавления будет ждать остальные все операции
//



// дз - ТОП100 используя блокирующие очереди






















}
