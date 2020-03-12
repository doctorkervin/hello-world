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
