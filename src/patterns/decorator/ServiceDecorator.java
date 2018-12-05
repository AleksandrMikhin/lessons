package patterns.decorator;

public class ServiceDecorator implements IService{

    private IService service;

    public ServiceDecorator(IService iService) {
        this.service = iService;
    }

    @Override
    public double getPrice() {
        return this.service.getPrice();
    }

    @Override
    public String getDescription() {
        return this.service.getDescription();
    }
}
