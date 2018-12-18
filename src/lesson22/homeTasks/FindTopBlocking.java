package lesson22.homeTasks;

//    Используя блокирующие очереди сосчитать топ слов как из предыдущего задания.
//    Вариантом решения может быть:
//      main создает потоки Worker, которые ждут строки из очереди на методе take()
//      Далее main читает с диска и добавляет строки в очереди, последними строками будут стоп-строки.
//
//    Worker'ы при получении стоп-строки добавляют свою внутреннюю мапу в следующую очередь и завершают свою работу.
//
//    main же ждет мапы со всех воркеров на второй очереди и результат собирает в результирующую мапу.
//
//    В итоге, не должно быть нигде явной синхронизации и использованы две блокирующие очереди


import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FindTopBlocking {

    final String stopString = "-";
    private BlockingQueue<String> newQueueWords = new ArrayBlockingQueue(10, true);

    private BlockingQueue<StringCount> tempQueue = new ArrayBlockingQueue(10, true);

    HashMap<String, Integer> wordsMap = new HashMap<>();


    public void addWord(String word){
        try {
            this.newQueueWords.put(word);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


    public class StringCount {
        String string;
        int count;

        public StringCount(String string, int count) {
            this.string = string;
            this.count = count;
        }
    }


    public class FindTopThread extends Thread {

        HashMap<String, Integer> localWordsMap = new HashMap<>();
        
        @Override
        public void run() {

            String str = "";
            Integer currentCount;

            while (true) {
                try {
                    str = newQueueWords.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (str.equals(stopString)) break;

                currentCount = localWordsMap.get(str);
                localWordsMap.put(str, (currentCount == null) ? 1 : ++currentCount);
            }

            try {
                for (HashMap.Entry<String, Integer> word : localWordsMap.entrySet()) {
                    tempQueue.put(new StringCount(word.getKey(), word.getValue()));
                }
                tempQueue.put(new StringCount(stopString, 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        int countThread = Runtime.getRuntime().availableProcessors();
        FindTopBlocking findTop = new FindTopBlocking();

        for (int i = 0; i < countThread; i++) {
            findTop.new FindTopThread().start();
        }

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
            for (int i = 0; i < countThread; i++) {
                findTop.addWord(findTop.stopString);
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так)");
        }

        Integer currentCount;
        StringCount stringCount = null;
        while (countThread > 0) {
            try {
                stringCount = findTop.tempQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stringCount.string.equals(findTop.stopString)) countThread--;
            else {
                currentCount = findTop.wordsMap.get(stringCount.string);
                findTop.wordsMap.put(stringCount.string, (currentCount == null) ? stringCount.count : currentCount + stringCount.count);
            }

        }

        String[] result = findTop.top(100);

        for (String word : result)
            System.out.println(word);
    }

}
