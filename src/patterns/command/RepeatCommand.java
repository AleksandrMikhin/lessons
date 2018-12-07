package patterns.command;

public class RepeatCommand extends Command {

    public RepeatCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "Repeat command";
    }

    @Override
    boolean execute() {
        if (processor.sizeStorage() > 0) {
            processor.addString(
                    processor.get(processor.sizeStorage() - 1));
            return true;
        }
        return false;
    }

}
