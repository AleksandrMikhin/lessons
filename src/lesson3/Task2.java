package lesson3;

public class Task2 {
    public static void main(String[] args) {
        int[] arr1 = new int[50];
        for (int i = 0; i<50; i++){
            arr1[i] = (i * 2) + 1;
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        for (int i = 49; i>0; i--){
            System.out.print(arr1[i] + " ");
        }
    }
}
