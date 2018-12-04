package tasksSyntax;

//Создать класс с методом getInstance(), который возвращает
//один и тот же экземпляр данного класса. Создание объектов извне запретить!

class Task6{

    public static final Task6 thisClass = new Task6();

    static Task6 getInstance(){
        return thisClass;
    }

}
