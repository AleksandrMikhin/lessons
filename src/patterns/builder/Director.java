package patterns.builder;

public class Director {

    private ComputerBuilder computerBuilder;

    public void setComputerBuilder(ComputerBuilder cb){
        this.computerBuilder = cb;
    }

    public ComputerBuilder getComputer() {
        return computerBuilder.getComputer();
    }
}
