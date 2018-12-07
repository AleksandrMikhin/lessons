package patterns.command;

public class ProcessCommand extends Command {

    public ProcessCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "Process command";
    }

    @Override
    boolean execute() {
//        processor. - методы доступны

        if (processor.lengthBuffer() > 0) {
            processor.add();
            return true;
        }
        return false;  //реализация - храним историю выполнения команд, для этого ответ
    }

}
