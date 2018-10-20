public class Task5_2 {
    public static void main(String[] args) {
        int summ = 1000, percent = 5, years = 1;
        double k;

        k = (double) percent/100/12 * Math.pow((1 + (double) percent/100/12), years*12)/(Math.pow((1 + (double ) percent/100/12), years*12) - 1);

        System.out.println( "Переплата по кредиту: " + (k * summ * years * 12 - summ) );

    }
}