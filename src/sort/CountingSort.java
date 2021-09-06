package sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {1,3,10,8,5,9,2};
        System.out.print(Arrays.toString(arr));
        System.out.println();
        sort(arr);
        System.out.print(Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        int MAX_VALUE = Integer.MIN_VALUE;
        for (int j : arr) {
            if (j > MAX_VALUE) {
                MAX_VALUE = j;
            }
        }

        int[] counts = new int[MAX_VALUE+1];
        for (int j : arr) {
            if (counts[j] == 0) {
                counts[j] = 0;
            }
            counts[j] += 1;
        }
        int index = 0;
        for (int i = 0; i < MAX_VALUE + 1 ; i++) {
            while (counts[i] > 0) {
                arr[index++] = i;
                counts[i]--;
            }
        }
    }
}
