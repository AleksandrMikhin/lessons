package task2;

public class Main {

    public static void main(String[] args) {
        ConfTypeChoice confChoice = new ConfTypeChoice();
        String typeCurFile = "txt"; // пришел файл тхт
        int countKey = 10;
        Config conf = confChoice.create(typeCurFile, countKey);
    }

}
