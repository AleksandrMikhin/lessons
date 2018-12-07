package lesson19.reflectoin;

//reflection api


public class Child extends Parent{

    private String name;
    private int version = 1;

//    private Parent parent;

    public Child(String name, int version) {
//        this.parent = new Parent(str1, i, str2); - так делать не хорошо)
//        this.parent = parent;                    - нужно так
        this.name = name;
        this.version = version;
    }

    private String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private String getInfo() {
        return "private getUnfo()";
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }

}

class Parent{
    public static String toStr( Object obj) {   // для ДЗ

        return new String();
    }
}

//    public static String toStr( Object obj) - для ДЗ
//    return - поля
//    Parent.toStr(child)
//   создаем аннотации, чтобы некоторые поля Child не попадали в toStr
