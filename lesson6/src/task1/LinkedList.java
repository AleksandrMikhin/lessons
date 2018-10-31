package task1;

public class LinkedList {
    protected int length = 0;
    protected Node firstNode, endNode;

    public int size(){ // возвращает размер списка
        return length;
    }

    @Override
    public String toString() {
        if (length == 0)
            return "Список пуст.";
        else {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(firstNode.value);

            Node tempNode = firstNode;
            for (int i = 1; i < length; i++){
                tempNode = tempNode.nextNode;
                strBuilder.append(" " + tempNode.value);
            }
            return strBuilder.toString();
        }
    }
}
