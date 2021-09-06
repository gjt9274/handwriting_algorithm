package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gjt9274
 * @Description 单峰数组求第k大的数
 */
public class DanFengKthLargest {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,9,10,8,6,2};
        int index = searchPeakIndex(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(index);
        System.out.println(findKthLargest(arr,index,2));
    }

    /**
     * 找到峰值所在索引
     * @param arr 输入数组
     * @return index
     */
    public static int searchPeakIndex(int[] arr) {
        int left = 0, right = arr.length - 1;
        //峰值可能在两端
        if (arr[left] > arr[left + 1]) {
            return 0;
        }
        if (arr[right] > arr[right - 1]) {
            return right;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }else if (arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static int findKthLargest(int[] arr, int index, int k) {
        if (k == 1) {
            return arr[index];
        }
        k -= 1;
        int i = index - 1;
        int j = index + 1;
        int len = arr.length;
        List<Integer> res = new ArrayList<>();
        while (k >= 1) {
            if (i >= 1 && arr[i] > arr[j]) {
                res.add(i);
                i--;
            }else if (j < len && arr[j] > arr[i]){
                res.add(j);
                j++;
            }
            k--;
        }

        return arr[res.get(res.size()-1)];

    }
}
