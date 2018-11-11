package lesson6.task1;

public class Main {
    public static void main(String... args) {
        LinkedList listList = new List();
        ((IList) listList).add(new Node(5), 1);
        ((IList) listList).add(new Node(7), 1);
        ((IList) listList).add(new Node(9), 1);
        System.out.println( listList.toString());

        System.out.println( ((List) listList).get(2).value);

        ((IList) listList).remove(2);
        System.out.println( listList.toString());

        ((IStack) listList).push(new Node(11));
        ((IStack) listList).push(new Node(15));
        System.out.println( listList.toString());

        ((IStack) listList).pop();
        System.out.println( listList.toString());

        ((IQueue) listList).shift(new Node(23));
        ((IQueue) listList).shift(new Node(48));
        System.out.println( listList.toString());

        ((IQueue) listList).unshift();
        System.out.println( listList.toString());

    }
}
