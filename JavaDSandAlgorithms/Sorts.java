import java.util.Scanner;

/*
 * Learning different types of sorting algorithms in Java
 */

public class Sorts {
    void printArray(int arr[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // SWAP
    void bubbleSort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // SELECT the min index
    void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }
    }

    // INSERT while loop and continue swapping
    void insertionSort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j = j - 1;
            }
            System.out.println(arr);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            Sorts bubsort = new Sorts();
            int[] arr = { 5, 1, 4, 2, 8 };
            int n = arr.length;

            System.out.print("Unsorted array: ");
            bubsort.printArray(arr, n);

            System.out.println();

            System.out.println("What kind of sort should I run? Enter a #");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Insertion Sort");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    bubsort.bubbleSort(arr, n);
                    System.out.print("Sorted array using Bubble Sort: ");
                    bubsort.printArray(arr, n);
                    break;
                case 2:
                    bubsort.selectionSort(arr, n);
                    System.out.print("Sorted array using Selection Sort: ");
                    bubsort.printArray(arr, n);
                    break;
                case 3:
                    bubsort.insertionSort(arr, n);
                    System.out.print("Sorted array using Insertion Sort: ");
                    bubsort.printArray(arr, n);
                    break;
            }

        } catch (Exception e) {
            System.out.println("Some kind of exception was caught: " + e);
        } finally {
            scan.close();
        }

    }
}