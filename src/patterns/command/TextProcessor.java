package patterns.command;


import java.util.ArrayList;
import java.util.Scanner;

public class TextProcessor {

    private CommandsHistory history = new CommandsHistory();

    private ArrayList<String> storage = new ArrayList<>();
    private String buffer = new String();

    final Command PROCESS = new ProcessCommand(this);
    final Command REPEAT = new RepeatCommand(this);
    final Command CANCEL = new CancelCommand(this);
    final Command EXIT = new ExitCommand(this);

    private void executeCommand(Command command) {
        if (command.execute()) {
            history.addCommand(command);
        }
    }


    public void add() {
        storage.add(buffer);
        buffer = "";
    }

    public void addString(String str) {
        storage.add(str);
    }

    public int lengthBuffer() {
        return buffer.length();
    }

    public int sizeStorage() {
        return storage.size();
    }

    public String get(int i) {
        return storage.get(i);
    }


    public Command delCommand() {
        return history.delCommand();
    }

    public Command lastCommand() {
        return history.lastCommand();
    }

    public boolean isEmptyCommand() {
        return history.isEmpty();
    }



    public void start() {
        Scanner in = new Scanner(System.in);
        String str;
        while (true) {
            str = in.nextLine();
            switch (str) {
                case "process":
                    executeCommand(PROCESS);
                    break;
                case "repeat": //повторим предыдущую
                    executeCommand(REPEAT);
                    break;
                case "cancel":
                    if (!history.isEmpty()) executeCommand(CANCEL);
                    break;
                case "exit":
                    executeCommand(EXIT);
                    return;
                 default: {
                     buffer = str;
                 }
            }
        }
    }

}
