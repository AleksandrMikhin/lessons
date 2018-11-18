package lesson11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class CollectionExamlpe {

//    set - не хранит неуникальные элементы
//    list - допускает


    public static void main(String[] args) throws IOException {

    //    ArrayList - класс расширяемый массив, дженерик - по умолчанию создает 10 эл, при превышении - увеличивается в полтора раза
    // LinkList - лучше использовать при добавлении/удалении элементов в середину. Работает с этим быстрее, чем ArrayList

        ArrayList<String> strList = new ArrayList<>();
        strList.ensureCapacity(20);
        strList.trimToSize(); //обрезает лишние пустые ячейки массива
        strList.add("Elem 1");
        strList.add("Elem 2");
        strList.add(0,"Elem 3");

        // удаление элементов
//        strList.remove(индекс);
//        strList.remove(значение);

        System.out.println(strList.toString());

        //коллекция из массива
        Integer[] integers = {23, 56, 78, 88};
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(integers));
        System.out.println(intList.toString());

        for (Integer i: intList){
            System.out.println(i);
        }

        Iterator iterator = intList.iterator();
        while (iterator.hasNext()){
//            System.out.println("iterator " + iterator.next());
            if (iterator.next() == (Integer) 56){
                iterator.remove();
            }
        }


//        LinkedList - реализует интерфейс двунаправленной очереди и лист Deque | List
//        связанный двунаправленный список

        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("String 1");
        stringLinkedList.add("String 2");
        stringLinkedList.add("String 3");
        stringLinkedList.add(1, "String 4");

        for(String str: stringLinkedList){
            System.out.println(str);
        }

//      удаление элементов
//        stringLinkedList.poll();        //null - если элемента нет
//        stringLinkedList.pollFirst();   //null
//        stringLinkedList.remove();      //выбросит исключение NoSuchElementException
//        stringLinkedList.removeFirst(); //выбросит исключение NoSuchElementException

        //массив из коллекции
        String[] arr = stringLinkedList.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));

        //Set - не может хранить дублирующие значения
        //HashSet - класс - самая быстрая по работе по удалению/добавления
        //SortedSet -> (TreeSet - хранятся в порядке возрастания - самая медленная
        //LinkedHashSet - хранатся так, как добавляли - 2я по скоросте

        HashSet<String> hashSet = new HashSet<>();  //если добавлять из массива, дублирующие элементы пропускаются
        //хранит элементы в порядке сортировкии по хэшу
        hashSet.add("Element 1");
        hashSet.add("Element 2");
        hashSet.add("Element 3");
        hashSet.add("Element 3");
        System.out.println(hashSet.toString());

        TreeSet<String> treeSet = new TreeSet<>();  //если добавлять из массива, дублирующие элементы пропускаются
        //хранит элементы в порядке сортировкии по хэшу
        treeSet.add("Element 2");
        treeSet.add("Element 1");
        treeSet.add("Element 3");
        treeSet.add("Element 3");
        System.out.println(treeSet.toString());


        Integer[] int2 = {23, 56, 78, 88, 454, 77, 74, 12, 74, 6};
        Set<Integer> set = new HashSet<>(Arrays.asList(int2));
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()){
            if (iter.next()>10)
                iter.remove();
        }
        System.out.println(set);

        // Чтение из файла в list
        File txtFile = new File("src/lesson11/txtfile.txt");
        List<String> list = Files.readAllLines(txtFile.toPath());
        System.out.println(list);






    }
}