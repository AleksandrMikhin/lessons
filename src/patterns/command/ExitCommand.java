package patterns.command;

public class ExitCommand extends Command {

    public ExitCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "ExitCommand";
    }

    @Override
    boolean execute() {
//        processor. - методы доступны
        return false;  //реализация - храним историю выполнения команд, для этого ответ
    }

//  команды repeat, cancel, exit

}
