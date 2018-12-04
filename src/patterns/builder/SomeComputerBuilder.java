package patterns.builder;

public class SomeComputerBuilder extends ComputerBuilder {
    @Override
    public Computer getComputer() {
        return super.getComputer();
    }

    @Override
    public void buildDisplay() {
        computer.setDisplay("some display");

    }

    @Override
    public void buildSystemBlock() {
        computer.setSystemBlock("some");
    }

    @Override
    public void buildManipulators() {
        computer.setManipulators("some");
    }
}
