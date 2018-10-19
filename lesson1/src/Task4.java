public class Task4 {
    public static void main(String[] args) {
        int m = 25, n = 100;
        System.out.println( "Ближайшее к 10 число: " + ((Math.abs(10-n)< Math.abs(10-m))? n : m) );
    }
}
