package patterns.command;

public class ProcessCommand extends Command {

    public ProcessCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "ProcessCommand";
    }

    @Override
    boolean execute() {
//        processor. - методы доступны
        return false;  //реализация - храним историю выполнения команд, для этого ответ
    }

//  команды repeat, cancel, exit

}
