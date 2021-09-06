package sort;

import java.util.Arrays;
import java.util.Collection;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1,3,10,8,5,9,2};
        System.out.print(Arrays.toString(arr));
        System.out.println();
        sort(arr, 0, arr.length-1);
        System.out.print(Arrays.toString(arr));

    }
    public static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right-left)/2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid ,right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
