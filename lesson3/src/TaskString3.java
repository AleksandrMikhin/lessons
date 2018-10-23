import java.util.Scanner;
import java.util.Arrays;

public class TaskString3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String str = in.nextLine();
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++)
            if (Character.isDigit(str.charAt(i)))
                strBuilder.append(str.charAt(i));
            else{
                if (strBuilder.length() > 0) {
                    if (strBuilder.charAt(strBuilder.length()-1) != ' ')
                        strBuilder.append(" ");
                }
            }

        String strArr[] = strBuilder.toString().split(" ");
        int numArr[] = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++)
            numArr[i] = Integer.parseInt(strArr[i]);
        System.out.println(Arrays.toString(numArr));
    }
}