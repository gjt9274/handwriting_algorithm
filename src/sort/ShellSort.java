package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {1,3,13,10,8,5,9,2};
        System.out.print(Arrays.toString(arr));
        System.out.println();
        sort(arr);
        System.out.print(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int len = arr.length;
        int step = 1;
        while (step < len / 3) {
            step = step * 3 + 1;
        }
        for (;step >= 1; step /= 3) {
            for (int i = step; i < len; i++) {
                int tmp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j+step] = tmp;
            }
        }
    }
}
