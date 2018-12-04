package patterns.factory;

//реализуют интерфейсами, а не абстрактными классами
abstract class Handler {
    abstract void read();
    abstract void write();
}

class TxtHandler extends Handler {
    @Override
    void read() {

    }

    @Override
    void write() {

    }
}

class XmlHandler extends Handler {
    @Override
    void read() {

    }

    @Override
    void write() {

    }
}


public class Factory {

    public Handler getHandler(String string) {

        Handler handler = null;
        if (string.endsWith("txt")) {
            handler = new TxtHandler();
        } else if (string.endsWith("xml")) {
            handler = new XmlHandler();
        }
        return handler;
    }
}
