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


////    Например, есть классы в пакете reflection:
////
//    interface I {
//        String getValue();
//    }
//
//    class Imlp implements I {
//        String getValue() {
//            return String.valueOf(Math.random());
//        }
//    }
//
//    class A {
//        private String str;
//
//        @Resource (Impl.class)
//        private I i;
//    }
//
//    class B {
//        private int val;
//
//        @Resource
//        private A aVal;
//
//        public I getI() {
//            return aVal.getI();
//        }
//    }
//
//    И следующий код создаст полноценный объект B, у которого все аннотированные поля будут инициализированы:
//
//    public static void main(String[] args) {
//            DIContext ctx = new DIContext();
//
//            B b = ctx.get("reflection.B"); // используйте generics, чтобы не делать cast
//
//            String randomString = b.getI().getValue();
//            I i = ctx.get("reflection.Impl");
//            }
//
//    Используйте свои исключения, которые будут выбрасываться в случае, если невозможно инициализировать объект.
