package lesson7.lesson.anonims;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc(3, 1);
        calc.execute(new Operation(){     // создание элемента анонимного класса из интерфейса с его переопределением метода
            @Override
            public double apply(double var1, double var2) {
                return var1 + var2;
            }
        });


        Calc calc1 = new Calc(3, 1);
        calc1.execute(new Operation(){     // создание элемента анонимного класса из интерфейса с его переопределением метода
            @Override
            public double apply(double var1, double var2) {
                return var1 - var2;
            }
        });

        calc1.execute(new Operation(){     // создание элемента анонимного класса из интерфейса с его переопределением метода
            @Override
            public double apply(double var1, double var2) {
                return var1 - var2 - 5;
            }
        });


    }
}
