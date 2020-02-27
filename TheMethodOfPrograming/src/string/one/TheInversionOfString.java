package string.one;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 字符串的反转
 */
public class TheInversionOfString<T> {

    /**
     * 数组反转
     * @param s
     */
    public void reverse(T[] s) {
        for (int l = 0, r = s.length - 1; l < r; l++, r--) {
            T temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
    }

    /**
     * 输入一个英文句子，翻转文中单词的顺序
     */
    public static void main(String[] args) {
        String test = "I am a Student ";
        String[] arr = test.split(" ");
        TheInversionOfString<String> temp = new TheInversionOfString<>();
        temp.reverse(arr);
        String collect = Arrays.asList(arr).stream().collect(Collectors.joining(" "));
        System.out.println(collect);
    }



    
    
}
