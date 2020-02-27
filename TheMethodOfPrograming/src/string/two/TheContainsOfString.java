package string.two;

import java.util.Arrays;

/**
 * 字符串的包含
 */
public class TheContainsOfString {

    /**
     * 判断str2的字符是否包含在str1里面
     * 使用位运算
     * @param str1
     * @param str2
     * @return
     */
    boolean stringContains(String str1, String str2){
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int hash = 0;
        for (int i = 0; i < arr1.length; i++) {
            hash |= (1 << (arr1[i] - 'A'));
        }

        for (int i = 0; i < arr2.length; i++) {
            if ((hash & (1 << (arr2[i] - 'A'))) == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 位运算有哪些？
     * 或运算符用符号“|”表示
     * 与运算符用符号“&”表示
     * 非运算符用符号“~”表示
     * 异或运算符是用符号“^”表示的
     * << 左移运算符，将运算符左边的对象向左移动运算符右边指定的位数（在低位补0）
     * >> "有符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。使用符号扩展机制，也就是说，如果值为正，则在高位补0，如果值为负，则在高位补1.
     * >>> "无符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。采用0扩展机制，也就是说，无论值的正负，都在高位补0.
     *
     * 注：x<<y 相当于 x*2y ；x>>y相当于x/2y
     */

    /**
     * 在一个字符串集合中寻找另一个字符串的变位词
     * 判断两个字符串是兄弟字符串
     * O(n*n)
     */

    boolean stringIsBrother(String str1 , String str2) {
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        if (arr2.length != arr1.length){
            return false;
        }
        int[] arr3 = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++ ){
                if (arr2[i] == arr1[j] && arr3[j] == 0){
                    arr3[j] = 1;
                    break;
                }
            }
            continue;
        }
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串str1是str2的兄弟字符串的第二中方法
     * @param str1
     * @param str2
     * @return
     * 时间复杂度  n*log(n) 主要来自于数组排序
     * Time Complexity n*log(n)
     */
    boolean stringIsBrother2(String str1 , String str2) {
        boolean flag = true;
        if (str1.length() != str2.length()){
            flag = false;
        }else {
            char[] arr1 = str1.toCharArray();
            Arrays.sort(arr1);
            char[] arr2 = str2.toCharArray();
            Arrays.sort(arr2);
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == arr2[i]){
                    continue;
                }else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        TheContainsOfString temp = new TheContainsOfString();
        boolean b = temp.stringIsBrother2("abcc", "bcc2");
        System.out.println(b);
    }
}
