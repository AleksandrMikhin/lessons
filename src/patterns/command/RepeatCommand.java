package patterns.command;

public class RepeatCommand extends Command {

    public RepeatCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "RepeatCommand";
    }

    @Override
    boolean execute() {
//        processor. - методы доступны
        return false;  //реализация - храним историю выполнения команд, для этого ответ
    }


}
