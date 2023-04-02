import java.util.Scanner;

/*
 * Learning how to manipulate arrays in Java
 */

public class Arrays {

    static void rotateArray(int[] arr, int d) {
        int n = arr.length;
        int[] temp = new int[n];

        int k = 0;
        for (int i = d; i < n; i++) {
            temp[k] = arr[i];
            k++;
        }
        for (int i = 0; i < d; i++) {
            temp[k] = arr[i];
            k++;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }

    }

    static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        System.out.println("Original array: ");
        printArray(arr);

        System.out.println();

        System.out.println("What would you like to rotate the array by: ");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        rotateArray(arr, x);
        printArray(arr);

        scan.close();
    }
}
