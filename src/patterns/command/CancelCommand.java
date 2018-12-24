package patterns.command;

public class CancelCommand extends Command {

    public CancelCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "Cancel command";
    }

    @Override
    boolean execute() {

        if (processor.lastCommand() != processor.CANCEL) {
            processor.delString(
                    processor.sizeStorage() - 1);
            processor.delCommand();
            return true;
        }
        return false;
    }

}
