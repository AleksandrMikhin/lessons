package patterns.builder;

abstract class ComputerBuilder {
    Computer computer;
    public Computer getComputer() {
        return computer;
    }

    public abstract void buildDisplay();
    public abstract void buildSystemBlock();
    public abstract void buildManipulators();


}
