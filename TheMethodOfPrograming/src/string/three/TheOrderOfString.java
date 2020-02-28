package string.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 字符串的排序
 */
public class TheOrderOfString {
    /**
     * 全排列递归实现
     */
    private List<String> quanpailie(char[] cs, int current) {
        //结果
        List<String> result = new LinkedList<>();
        //当前指向数组最后一位时,将数组(全排列的一种)输出到结果集里
        if (current == cs.length - 1) {
            result.add(Arrays.toString(cs));
        } else {
            //循环改变数组的第一个位置的值,并求剩下的其他字符的全排列,并装入结果集.
            for (int i = current; i < cs.length; i++) {
                //交换当前字符与下一字符
                swap(cs, current, i);
                //这一块难理解,相当于,在A确定放在第一位的时候,求BC的全排列,并且加上A,形成ABC,ACB放入结果集.
                result.addAll(quanpailie(cs, current + 1));
                //交换回来,方便下一次交换.
                swap(cs, current, i);
            }
        }
        return result;
    }

    /**
     * 交换数组第b,e位置上的值
     */
    private void swap(char[] cs, int b, int e) {
        char tmp = cs[b];
        cs[b] = cs[e];
        cs[e] = tmp;
    }

    /**
     * 可以看出,ABC的全排列为:
     * <p>
     * (A+(BC的全排列)) + (B+(AC的全排列)) +  (C + (AB的全排列)).
     *
     * @param str
     * @return
     */
    private List<StringBuffer> orderString(String str) {
        char[] chars = str.toCharArray();
        List<StringBuffer> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            String other;
            if (i == 0){
                other = str.substring(i+1);
            }else {
                other = str.substring(0,i)+str.substring(i+1);
            }
            List<StringBuffer> strings = orderString(other);
            if (strings.size() == 0){
                result.add(new StringBuffer(String.valueOf(chars[i])));
            }else {
                for (int j = 0; j < strings.size(); j++) {
                    result.add(new StringBuffer(String.valueOf(chars[i])).append(strings.get(j).toString()));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TheOrderOfString tmp = new TheOrderOfString();
       /* char[] arr = {'A','b','c'};
        //System.out.println(Arrays.toString(arr));
        TheOrderOfString tmp = new TheOrderOfString();
        List<String> quanpailie = tmp.quanpailie(arr, 0);
        quanpailie.forEach(System.out::println);*/

        String str = "abc";
        //extractStringOneChar(str);
        List<StringBuffer> strings = tmp.orderString(str);
        strings.forEach(System.out::println);


    }

    /**
     * 从头到尾依次遍历字符串的字符的剩余字符串
     * @param str
     */
    private static void extractStringOneChar(String str) {
        if (str.length() > 1){
            for (int i = 0; i < str.length(); i++) {
                if (i == 0){
                    System.out.println(str.substring(i+1));
                }else {
                    System.out.println(str.substring(0,i)+str.substring(i+1));
                }
            }
        }else{
            System.out.println(str);
        }

    }
}
