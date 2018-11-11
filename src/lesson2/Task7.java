package lesson2;

public class Task7 {
    public static void main(String[] args) {
        int n = 0;
        for (int hour = 0; hour < 24; hour++) {
            if ((hour % 10) > 5) {continue;}
            n++;
        }
        System.out.println("Симметричных комбинаций: " + n);
    }
}
