package array.one;

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
}
