package task2;

public class XmlHandler extends Config {

    public XmlHandler(int count) {
        keysVolume = new KeyVolume[count];
    }

    @Override
    public void changeKey() {
        System.out.println("Исменяем ключ в XML-файле");
    }

    @Override
    public void getKey() {
        System.out.println("Считываем ключ из XML-файла");
    }
}
