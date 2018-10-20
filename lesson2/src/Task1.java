public class Task1 {
    public static void main(String[] args) {
        int a = 18, b = -15, c = 64, x, y, z;

        x = (a<b) ? ((a<c) ? a : c) : (b<c)? b : c;
        z = (a>b) ? ((a>c) ? a : c) : (b>c)? b : c;
        y = (a!=x && a!=z) ? a : (b!=x && b!=z) ? b : c;

        a = x;
        b = y;
        c = z;

        System.out.println( "a, b, c: " + x + ", " + y + ", " + z );
    }
}
