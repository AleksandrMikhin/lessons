import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество тарелок: ");
        int plates = in.nextInt();
        System.out.print("количество средства: ");
        double fairy = in.nextDouble();
        while (plates>0) {
            if (fairy >= 0.5){
                plates -= 1;
                fairy -= 0.5;
                System.out.print(" " + fairy);
            } else {
                break;
            }
        }
        System.out.print( "\n" + ((fairy > 0) ? "\nОстаток средства: " + fairy : "") +
                ((plates > 0) ? "\nОстаток тарелок: " + plates : ""));
    }
}
