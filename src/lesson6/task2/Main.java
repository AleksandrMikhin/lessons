package lesson6.task2;

public class Main {

    public static void main(String[] args) {
        ConfTypeChoice confChoice = new ConfTypeChoice();
        String typeCurFile = "txt"; // пришел файл тхт
        int countKeys = 10;
        Config conf = confChoice.create(typeCurFile, countKeys);

        conf.getKey();
        conf.changeKey();

    }

}
