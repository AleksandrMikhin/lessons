package lesson6.task2;

public class TxtHandler extends Config {

    public TxtHandler(int count) {
        keysVolume = new KeyVolume[count];
    }

    @Override
    public void changeKey() {
        System.out.println("Исменяем ключ в TXT-файле");
    }

    @Override
    public void getKey() {
        System.out.println("Считываем ключ из TXT-файла");
    }
}
