package lesson19.homeTasks.task2;

//    Написать простой Dependency Injection Framework.
//    Всю логику можно заключить в классе DIContext, благодаря которому можно создавать экземпляр
//    любого класса с автоматически установленными зависимостями.


public class Main {

    public static void main(String[] args) {

        DIContext diContext = new DIContext();

        try {
            Pencil randomPencil = diContext.get("lesson19.homeTasks.task2.Pencil");
            System.out.println("randomPencil : " + randomPencil);

            System.out.println("--------------");

            Box boxPencil = diContext.get("lesson19.homeTasks.task2.Box");
            System.out.println("Box pencils : " + boxPencil.getCount());

            boxPencil.listPencils();

        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace(); // что-то пошло не так
        }

    }
}