package sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1,3,22,10,8,5,9,2};
        System.out.print(Arrays.toString(arr));
        System.out.println();
        sort(arr);
        System.out.print(Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i -1 ; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
}