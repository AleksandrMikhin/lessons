package lesson6.task1;

public class List extends LinkedList
        implements IList, IStack, IQueue{

    @Override
    public void add(Node addNode, int index) { // добавляет объект на указанную позицию (index)
        if (index == 1) {
            if (firstNode == null)
                firstNode = addNode;
            else {
                addNode.setNextNode(firstNode);
                firstNode = addNode;
            }
            length++;
        } else {
            if (index > length)
                System.out.println("Список короче введенного индекса.");
            else {
                Node tempNode = firstNode;
                for (int i = 1; i < index; i++) tempNode = tempNode.getNextNode();
                addNode.setNextNode(tempNode.getNextNode());
                tempNode.setNextNode(addNode);
                length++;
            }
        }
    }

    @Override
    public void remove(int index) { // удаляет элемент с указанной позиции (index)
        if (index > length)
            System.out.println("Список короче введенного индекса.");
        else {
            if (index == 1)
                firstNode = firstNode.getNextNode();
            else {
                Node tempNode = firstNode;
                for (int i = 1; i < index - 1; i++) tempNode = tempNode.nextNode;
                tempNode.setNextNode(tempNode.getNextNode().getNextNode());
            }
            length--;
        }
    }

    @Override
    public Node get(int index) { // - находит и возвращает элемент по индексу (index)
        if (index == 1)
            return firstNode;
        else {
            Node tempNode = firstNode;
            for (int i = 1; i < index - 1; i++) tempNode = tempNode.nextNode;
            return tempNode.getNextNode();
        }
    }

    @Override
    public void push(Node addNode) { // добавляет объект в конец списка
        if (length == 0)
            firstNode = addNode;
        else {
            Node tempNode = firstNode;
            for (int i = 1; i < length; i++) tempNode = tempNode.nextNode;
            tempNode.setNextNode(addNode);
        }
        length++;
    }

    @Override
    public void pop() { // удаляет элемент из конца списка
        length--;
    }


    @Override
    public void shift(Node addNode) { // добавляет объект в начало списка
        if (length == 0) {
            firstNode = addNode;
        } else {
            addNode.setNextNode(firstNode);
            firstNode = addNode;
        }
        length++;
    }

    @Override
    public void unshift() { // удаляет элемент из начала списка
        if (length > 0) {
            if (length == 1)
                firstNode = null;
            else
                firstNode = firstNode.getNextNode();
            length--;
        } else
            System.out.println("Список пуст");
    }
}
