package lesson16.homeTasks.task1;

import java.io.*;

public class Command implements Externalizable {
    private String command;
    private String sender;


    public Command() {
        this.sender = null;
        this.command = null;
    }

    public Command(String sender, String command) {
        this.sender = sender;
        this.command = command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCommand() {
        return command;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return sender + ": " + command;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(sender);
        out.writeUTF(command);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.sender = in.readUTF();
        this.command = in.readUTF();
    }
}
