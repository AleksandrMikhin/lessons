package task1;

public interface IList {

    void add(Node addNode, int index); // добавляет объект на указанную позицию (index)

    void remove(int index); // удаляет элемент с указанной позиции (index)

    Node get(int index); // - находит и возвращает элемент по индексу (index)

}