import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class Task6 {
    public static void main(String[] args) {
        double a = 1, b = 20, c = 30, d;
        d = Math.pow(b, 2) - 4*a*c;
        System.out.println( (d<0)? "Нет решений." :
                ("X1 = " + (-b - Math.pow(d, 0.5)) /(2*a)) + "; X2 = " + (-b + Math.pow(d, 0.5)) /(2*a));

    }
}
