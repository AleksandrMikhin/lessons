package patterns.command;

import java.util.Stack;

public class CommandsHistory {
    private Stack<Command> history = new Stack<>();

    public void addCommand(Command command) {
        history.push(command);
    }

    public void delCommand() {
        history.pop();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

}
