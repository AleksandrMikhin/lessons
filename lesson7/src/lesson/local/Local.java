package lesson.local;

public class Local {
    public static void main(String[] args) {

        class LocalClass{
            String name = "LocalClass";

            public String getName() {
                return name;
            }
        }

        LocalClass localClass = new LocalClass();
        System.out.println(localClass.getName());
    }
}
