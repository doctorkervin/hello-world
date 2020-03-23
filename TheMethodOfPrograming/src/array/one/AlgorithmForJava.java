package array.one;

public class AlgorithmForJava {


}

/**
 * 分治算法
 * 作为分治法里很典型的一种算法，合并排序和快速排序充分展现了分治法的思想，分而治之，在此次编程使用此方法中，给我的体会是程序简单分为两部分，
 * 第一部分，不断“拆”，缩小子问题规模，达到最优子结构。然后合并，在合并过程中，应为子问题足够小，容易计算，再者不断合并子问题答案，最终求出问题解
 *
 */
class SplitAndMerge {
    /**
     * 函数说明：在数组被拆分以后进行合并
     */
    static void Merge(int a[], int left, int middle, int rigth) {
        //定义左端数组大小
        int n1 = middle - left + 1;


        int n2 = rigth - middle;

        //初始化数组，分配内存
        int bejin[] = new int[n1];
        int end[] = new int[n2];

        //数组赋值
        for (int i = 0; i < n1; i++)
            bejin[i] = a[left + i];

        for (int i = 0; i < n2; i++)
            end[i] = a[middle + 1 + i];

        //用key做原数组索引，没调用一次函数重新给原数组付一次值
        int i = 0, j = 0, key;
        for (key = left; key <= rigth; key++) {

            if (n1 > i && n2 > j && i < n1 && bejin[i] <= end[j])
                a[key] = bejin[i++];
            else if (n1 > i && n2 > j && j < n2 && bejin[i] >= end[j])
                a[key] = end[j++];
            else if (i == n1 && j < n2)
                a[key] = end[j++];
            else if (j == n2 && i < n1)
                a[key] = bejin[i++];
        }
    }

    /**
     * 差分数组区间，不断分支
     */
    static void MergeSort(int a[], int left, int rigth) {
        int middle = 0;
        if (left < rigth) {
            middle = (rigth + left) / 2;
            MergeSort(a, left, middle);
            MergeSort(a, middle + 1, rigth);
            Merge(a, left, middle, rigth);
        }
    }

    public static void main(String[] args) {
        int a[] = {85, 3, 52, 9, 7, 1, 5, 4};
        MergeSort(a, 0, 7);
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + a[i]);
        }

    }
}

/**
 * 分治快排
 */
class FastSort {
    /**
     * 交换函数，i，j为数组索引
     */
    static void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * 选取一个关键字(key)作为枢轴，一般取整组记录的第一个数/最后一个，这里采用选取序列最后一个数为枢轴。
     * 设置两个变量left = 0;right = N - 1;
     * 从left一直向后走，直到找到一个大于key的值，right从后至前，直至找到一个小于key的值，然后交换这两个数。
     * 重复第三步，一直往后找，直到left和right相遇，这时将key放置left的位置即可。
     *
     * @return
     */
    static int PartSort(int[] array, int left, int right) {
        int key = array[right];//定义基准
        int count = right;//保存rigth值
        while (left < right)//防止数组越界
        {
            while (left < right && array[left] <= key) {
                ++left;
            }
            while (left < right && array[right] >= key) {
                --right;
            }
            swap(array, left, right);
        }
        swap(array, right, count);
        return right;
    }

    /**
     * 分治思想，递归调用
     */
    static void QuickSort(int array[], int left, int right) {
        if (left >= right)//表示已经完成一个组
        {
            return;
        }
        int index = PartSort(array, left, right);//枢轴的位置
        QuickSort(array, left, index - 1);
        QuickSort(array, index + 1, right);
    }

    public static void main(String[] args) {
        int a[] = {1, 5, -5, 54, 15, 67, 16, 23};
        QuickSort(a, 0, 7);
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.print("\n");
    }
}
