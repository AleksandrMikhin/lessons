package lesson.anonims;

public class Calc {
    int a;
    int b;
    double res;

    public Calc(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void execute(Operation operation){
        res = operation.apply(a, b);
    }

    public void showRes() {
        System.out.println("res = " + res);
   }

}
