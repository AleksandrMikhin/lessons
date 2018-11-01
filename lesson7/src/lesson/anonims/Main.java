package lesson7.anonims;

public class Main {
    public static void main(String[] args) {
        Calc calc = new Calc(3, 1);
        calc.summ(new Operation(){     // создание элемента анонимного класса из интерфейса с его переопределением метода
            @Override
            public double apply(double var1, double var2) {
                return var1 + var2;
            }
        });


        Calc calc1 = new Calc(3, 1);
        calc1.minus(new Operation(){     // создание элемента анонимного класса из интерфейса с его переопределением метода
            @Override
            public double apply(double var1, double var2) {
                return var1 - var2;
            }
        });

        calc1.minus(new Operation(){     // создание элемента анонимного класса из интерфейса с его переопределением метода
            @Override
            public double apply(double var1, double var2) {
                return var1 - var2 - 5;
            }
        });


    }
}
