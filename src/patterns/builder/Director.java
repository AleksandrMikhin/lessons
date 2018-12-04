package patterns.builder;

public class Director {

    private ComputerBuilder computerBuilder;

    public void setComputerBuilder(
            ComputerBuilder computerBuilder){
        this.computerBuilder = computerBuilder;
    }

    public Computer getComputer(){
        return computerBuilder.getComputer();
    }

    public void constructComputer(){
        computerBuilder.createComputer();
        computerBuilder.buildDisplay();
        computerBuilder.buildManipulators();
        computerBuilder.buildSystemBlock();
    }

}
