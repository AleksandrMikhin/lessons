package patterns.command;

import java.util.Stack;

public class CommandsHistory {
    private Stack<Command> history = new Stack<>();

    public void addCommand(Command command) {
        history.push(command);
    }

    public Command delCommand() {
        return history.pop();
    }

    public Command lastCommand() {
        return history.peek();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

}
