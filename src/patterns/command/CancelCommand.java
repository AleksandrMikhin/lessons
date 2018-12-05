package patterns.command;

public class CancelCommand extends Command {

    public CancelCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "CancelCommand";
    }

    @Override
    boolean execute() {
//        processor. - методы доступны
        return false;  //реализация - храним историю выполнения команд, для этого ответ
    }

//  команды repeat, cancel, exit

}
