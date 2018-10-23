import java.util.Scanner;

public class TaskString1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = in.nextLine();
        System.out.print("Введите подстроку для поиска: ");
        String strFind = in.nextLine();
        System.out.print("Введите строку для замены: ");
        String strReplace = in.nextLine();

        if (str.indexOf(strFind) >= 0){
            System.out.println(str.replace(strFind, strReplace));
        }else{
            System.out.println("Подстрока не найдена.");
        }
    }
}
