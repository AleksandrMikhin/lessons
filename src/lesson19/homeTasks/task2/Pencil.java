package lesson19.homeTasks.task2;

public class Pencil {

    @InjectRandomColor
    Color color;

    @Override
    public String toString() {
        return "Pencil{" +
                "color=" + color +
                '}';
    }
}
