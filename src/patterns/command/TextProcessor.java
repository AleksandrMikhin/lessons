package patterns.command;

import java.util.Scanner;

public class TextProcessor {
    private CommandsHistory history = new CommandsHistory();
    //перечисляем команды константы классов или в enum


    private void executeCommand(Command command) {
        if (command.execute()) {
            history.addCommand(command);
        }
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        while (true) {
            switch (in.nextLine()) {
                case "process":
                    executeCommand(new ProcessCommand(this));
                    break;
                case "repeat": //повторим предыдущую
                    executeCommand(new RepeatCommand(this));
                    break;
                case "cancel":
                    executeCommand(new CancelCommand(this));
                    break;
                case "exit":
                    executeCommand(new ExitCommand(this));
                    return;
            }
        }
    }

}
