package lesson19.homeTasks.task2;

import java.util.List;

public class Box implements InterfaceForBox {

    @InjectRandomPencils(min = 5, max = 15)
    private List<Pencil> pencils;

    @Override
    public void listPencils() {
        for (int i = 0; i < pencils.size(); i++) {
            System.out.println((i + 1) + ". " + pencils.get(i));
        }
    }

    @Override
    public int getCount() {
        return pencils.size();
    }
}
