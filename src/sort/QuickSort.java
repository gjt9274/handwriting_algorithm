package sort;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1,3,10,0,8,5,9,2};
        System.out.print(Arrays.toString(arr));
        System.out.println();
        sort(arr, 0, arr.length-1);
//        qSort(arr, 0, arr.length - 1);
        System.out.print(Arrays.toString(arr));
    }
    public static void sort(int[] arr,int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(arr, left, right);
        sort(arr, left, p-1);
        sort(arr, p+1, right);
    }
    public static int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr,index-1,pivot);
        return index-1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void qSort(int[] a, int left, int right) {
        int pivot;
        if (left >= right) {
            return;
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.push(left);
        stack.push(right);
        while (!stack.isEmpty()) {
            right = stack.pop();
            left = stack.pop();
            pivot = partition(a, left ,right);
            if (left < pivot - 1) {
                stack.push(left);
                stack.push(pivot - 1);
            }
            if (pivot + 1 < right) {
                stack.push(pivot + 1);
                stack.push(right);
            }
        }
    }
}
