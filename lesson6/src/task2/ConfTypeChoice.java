package task2;

public class ConfTypeChoice {
    public Config create(String typeFile, int count){
        if (typeFile.equals("xml")) {
            return new XmlHandler(count);
        }
        if (typeFile.equals("txt")) {
            return new TxtHandler(count);
        }
        return null;
    }

}
