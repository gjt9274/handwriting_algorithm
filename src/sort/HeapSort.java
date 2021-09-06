package sort;

import java.util.Arrays;


public class HeapSort {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] arr = {1,3,10,8,5,4,9,2};
        System.out.print(Arrays.toString(arr));
        System.out.println();
        heapSort.sort(arr);
        System.out.print(Arrays.toString(arr));

    }
    public void sort(int[] arr) {
        int len = arr.length;
        buildMaxHeap(arr,len);
        for (int i = len-1; i > 0; i--) {
            swap(arr, 0, i);//将最大的元素，换到数组尾部，然后对前面i-1个元素，重新建堆
            heapify(arr,0,i);
        }
    }
    public void buildMaxHeap(int[] arr,int len) {
        for (int i = (len - 1) / 2; i >= 0; i--) {
            heapify(arr,i,len);
        }
    }

    private void heapify(int[] arr, int parent, int length) {
        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        int largest = parent;
        if (left < length && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < length && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != parent) {
            swap(arr, parent, largest);
            heapify(arr, largest,length);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
