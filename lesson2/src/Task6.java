public class Task6 {
    public static void main(String[] args) {
        int a, b, c, d, e, f, happy = 0;
        for (int number=0; number<999999; number++) {
            a = number / 100000;
            b = number / 10000 % 10;
            c = number / 1000 % 10;
            d = number / 100 % 10;
            e = number / 10 % 10;
            f = number % 10;
            if ((a + b + c) == (d + e + f)) {happy++;}
        }
        System.out.println( "Количество: " + happy);
    }
}
