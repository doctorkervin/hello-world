package array.one;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: doctor kervin
 * @date: 2020-03-08 23:35
 **/
public class TheSmallKofArray {
    /**
     * 有n个数请找出最小的k个数
     */
    public static void main(String[] args) {
        method01();
        method2();
        method3();
        method4();

    }
    /**
     * 方法四 线性选择算法
     * 最优：
     */
    private static void method4() {
    }
    /**
     * 方法三 部分排序,额外创建一个长度为k的堆
     * 在堆中进行查找和更新的时间复杂读均为O(logk)
     *
     */
    private static void method3() {
    }
    /**
     * 方法二 部分排序,额外创建一个长度为k的数组
     * 在数组中进行查找的时间复杂度为O(k)
     */
    private static void method2() {
    }

    /**
     * 方法一 全部排序
     */
    private static void method01() {

    }

    /**
     * 排序
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> al = new ArrayList<>();
        if(input.length <= 0 || input == null || k <= 0 || k > input.length){
            return al;
        }
        Arrays.sort(input);
        for(int i = 0; i < k; i++){
            al.add(input[i]);
        }
        return al;
    }

    //O(n)
    public ArrayList<Integer> getLeastNumbers(int nums[], int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int lens = nums.length;
        if (nums == null || lens == 0 || k > lens || k <= 0) {
            return list;
        }
        int start = 0;
        int end = lens - 1;
        int index = partition(nums, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(nums, start, end);
            } else {
                start = index + 1;
                index = partition(nums, start, end);
            }
        }
        for (int i = 0; i < k; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    //快排
    private int partition(int[] nums, int start, int end) { //快排
        int privotKey = nums[start];
        while (start < end) {
            while (start < end && privotKey <= nums[end]) {
                --end;
            }
            swap(nums, start, end);//交换位置，使大于privotkey的值位于数组右边

            while (start < end && privotKey >= nums[start]) {
                ++start;
            }
            swap(nums, start, end);//交换位置，使小于privotkey的值位于数组左边
        }
        return start;
    }

    //交换数组元素位置
    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

}

/**
 * @author jyroy
 * 冒泡排序常规版
 */
class BubbleSortNormal {
    public static void main(String[] args) {
        int[] list = {3,4,1,5,2};
        int temp = 0; // 开辟一个临时空间, 存放交换的中间值
        // 要遍历的次数
        for (int i = 0; i < list.length-1; i++) {
            System.out.format("第 %d 遍：\n", i+1);
            //依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上
            for (int j = 0; j < list.length-1-i; j++) {
                // 比较相邻的元素，如果前面的数小于后面的数，就交换
                if (list[j] < list[j+1]) {
                    temp = list[j+1];
                    list[j+1] = list[j];
                    list[j] = temp;
                }
                System.out.format("第 %d 遍的第%d 次交换：", i+1,j+1);
                for(int count:list) {
                    System.out.print(count);
                }
                System.out.println("");
            }
            System.out.format("第 %d 遍最终结果：", i+1);
            for(int count:list) {
                System.out.print(count);
            }
            System.out.println("\n#########################");
        }
    }
}

/**
 * 基数排序
 * 考虑负数的情况还可以参考：
 */
   class RadixSort {

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr, maxDigit);
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    protected int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    private int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
