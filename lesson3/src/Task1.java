import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        int[] arr1 = new int[10];
        for (int i = 0; i<10; i++){
            arr1[i] = (i + 1)*2;
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        for (int n:arr1){
            System.out.println( n );
        }
    }
}
