package lesson6.task2;

public class XmlHandler extends Config {

    public XmlHandler(int count) {
        keysVolume = new KeyVolume[count];
    }

    @Override
    public void changeKey() {
        System.out.println("Изменяем ключ в XML-файле");
    }

    @Override
    public void getKey() {
        System.out.println("Считываем ключ из XML-файла");
    }
}
