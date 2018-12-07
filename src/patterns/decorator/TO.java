package patterns.decorator;

public class TO implements IService {

    @Override
    public double getPrice() {
        return 3000;
    }

    @Override
    public String getDescription() {
        return "TO";
    }
}
