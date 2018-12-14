package lesson16.homeTasks.task1;

import java.io.*;

public class Message implements Externalizable {
    private String messText;
    private String sender;

    public Message() {
        this.sender = null;
        this.messText = null;
    }

    public Message(String sender, String messText) {
        this.sender = sender;
        this.messText = messText;
    }

    public void setMessText(String messText) {
        this.messText = messText;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessText() {
        return messText;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return sender + ": " + messText;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(sender);
        out.writeUTF(messText);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.sender = in.readUTF();
        this.messText = in.readUTF();
    }
}
