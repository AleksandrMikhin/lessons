package task2;

public class List {
    private int length = 1;
    private Node firstNode;

    public List(int value) {
        firstNode.value = value;
    }

    public int getLength() {
        return length;
    }

    public void addNode(int value) {
        Node tempNode = firstNode;
        for (int i = 1; i<length; i++) tempNode = tempNode.nextNode;
        tempNode.nextNode = new Node(value);
        length++;
    }


    public void removeNode(int number) {
        Node tempNode = firstNode;

        if ((length < number) || (length == 1)){
            System.out.println("Невозможно удалить единственный элемент либо его номер больше, чем длина списка");
        }else{
            if (number == 1) firstNode = firstNode.nextNode;
            else{
                for (int i = 0; i < number-2; i++) tempNode = tempNode.nextNode;
                tempNode.nextNode = tempNode.nextNode.nextNode;
            }
            length--;
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        Node tempNode = firstNode;
        strBuilder.append(firstNode.value);
        for (int i = 0; i<length; i++){
            tempNode = tempNode.nextNode;
            strBuilder.append(" " + tempNode.value);
        }
        return strBuilder.toString();
    }
}