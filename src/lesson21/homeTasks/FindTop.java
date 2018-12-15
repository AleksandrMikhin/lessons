package lesson21.homeTasks;

//выполнить подсчет топ100 слов парралельно
//Количество потоков должно быть равно числу доступных процессоров системы

//считать все из файла в коллекцию а потом ищем топ100 слов
//каждый поток собирает данные в свой Map (кусок файла)
//поток 1й, который создавал потоки - ждет завершения всех остальных - отображает результат
//Runtime.getRuntime().availableProcessors();

import java.io.*;
import java.util.*;

public class FindTop {

    private ArrayList<String> words = new ArrayList<>();
    HashMap<String, Integer> wordsMap = new HashMap<>();

    public void addWord(String word){
        this.words.add(word);
    }

    public int wordsCount(){
        return words.size();
    }

    public String[] top(int countTop){

        ArrayList<String> topWords = new ArrayList(0);
        ArrayList<Integer> topCount = new ArrayList(0);

        boolean flag;
        for (HashMap.Entry<String, Integer> word : wordsMap.entrySet()) {
            int value = word.getValue();

            flag = false;
            for (int i = 0; i < topCount.size(); i++) {

                if (value >= topCount.get(i)) {
                    topCount.add(i, value);
                    topWords.add(i, word.getKey());
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                topCount.add(value);
                topWords.add(word.getKey());
            }
        }


        return topWords.toArray(new String[(countTop < topWords.size())? countTop: topWords.size()]);
    }


    public class FindTopThread extends Thread {
        private int start, count;

        public FindTopThread(int start, int count) {
            this.start = start;
            this.count = count;
        }

        @Override
        public void run() {
            String str;
            Integer currentCount;
            for (int i = start; i < start + count; i++) {
                str = words.get(i);

                synchronized (FindTop.class) {
                    currentCount = wordsMap.get(str);
                    wordsMap.put(str, (currentCount == null) ? 1 : ++currentCount);
                }

            }
        }
    }



    public static void main(String[] args) {

        int countThread = Runtime.getRuntime().availableProcessors();
        List<FindTop.FindTopThread> listThread = new ArrayList<>(countThread);

        FindTop findTop = new FindTop();

        File file = new File("file.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String str;
            while (!((str = bufferedReader.readLine()) == null)) {
                if (str != null && str.length()>0) {
                    String[] arr = str.split("\\s+|\\(+|\\)+|\\,+|\\.+|\\!+|\\?+|\\-+|\\++|\\=+|\\*+|\\:+|\\;+|\\'+|\"+|\\\\+|/+");

                    for (String s : arr) {
                        if (s.length() > 0) findTop.addWord(s.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так)");
        }


        int size = findTop.wordsCount();
        int startPos = 0, ost = size % countThread;

        for (int i = 0; i < countThread; i++) {
            listThread.add(findTop.new FindTopThread(startPos, size / countThread + ((ost > 0) ? 1 : 0)));
            startPos += size / countThread + ((ost-- > 0) ? 1 : 0);
        }


        for (FindTop.FindTopThread topThread: listThread) {
            topThread.start();
        }


        for (FindTop.FindTopThread topThread: listThread) {
            try {
                topThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String[] result = findTop.top(100);

        for (String word : result)
            System.out.println(word);
    }

}
