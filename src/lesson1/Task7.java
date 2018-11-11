package lesson1;

public class Task7 {
    public static void main(String[] args) {
        int a = 173, one, two, three;
        three = (int) a / 100;
        two = (int) (a - three * 100)  / 10;
        one = a - three * 100 - two * 10;
        System.out.println( "Наибольшее число: " + ((one>two)? ((one>three)? one:three) : ((two>three)? two:three)));

    }
}
