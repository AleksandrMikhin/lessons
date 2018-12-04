package tasksSyntax;

//Создать классы Dog, Cat и Mouse.
//        Реализовать интерфейсы (Movable, Eatable, Eat ) в классах, учитывая что:
//        - Кот может передвигаться, может кого-то съесть и может быть сам съеден.
//        - Мышь может передвигаться и может быть съедена.
//        - Собака может передвигаться и съесть кого-то.

interface IMovable{
}

interface IEatable{
}

interface IEat{
}

class CatFromTask5 implements IMovable, IEatable, IEat {
}

class MouseFromTask5 implements IMovable, IEatable {
}

class DogFromTask5 implements IMovable, IEat {
}


public class Task5 {

}
