package string.one;

import org.junit.Test;

/**
 * 字符串的反转
 */
public class TheInversionOfString {

    public void reverseString(char[] s) {
        for (int l = 0, r = s.length - 1; l < r; l++, r--) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
    }

    @Test
    public void test(){
        String temp = "123sdasffgv";
        char[] arr = temp.toCharArray();
        reverseString(arr);
        System.out.println(arr.toString());
    }

    public static void main(String[] args) {
        TheInversionOfString temp1 = new TheInversionOfString();
        String temp = "123sdasffgv";
        char[] arr = temp.toCharArray();
        temp1.reverseString(arr);
        System.out.println(arr.toString());
    }
}
