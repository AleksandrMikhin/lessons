package patterns.command;

public class ExitCommand extends Command {

    public ExitCommand(TextProcessor processor) {
        super(processor);
    }

    @Override
    String name() {
        return "Exit command";
    }

    @Override
    boolean execute() {

        System.out.println("-------------- Введенный текст --------------");
        for (int i = 0; i < processor.sizeStorage(); i++) {
            System.out.println(processor.get(i));
        }
        System.out.println("\n--- Введенные команды начиная с последней---");

        while (!processor.isEmptyCommand()) {
            System.out.println(processor.delCommand().name());
        }

        return true;
    }

}
