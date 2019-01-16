import org.apache.log4j.Logger;

public class SomeClass {

    private static final Logger LOG = Logger.getLogger(SomeClass.class);
    public static void main(String[] args) {
        LOG.info("Application started");
        System.out.println("Hello, Maven!");
        LOG.info("Application stopped");

    }
}
