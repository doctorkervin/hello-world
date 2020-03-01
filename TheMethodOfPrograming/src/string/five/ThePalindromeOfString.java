package string.five;
/**
 * 字符串中的回文
 */
public class ThePalindromeOfString {
    /**
     * 回文串，就是给定一个字符串，正着读，反着读是一样的
     */
    boolean theStringIsPalindrome(String str){
        int length = str.length();
        if (str == null || length == 0) {
            return false;
        }
        char[] chars = str.toCharArray();
        //不管是String长度是奇数还是偶数
        for (int i = 0; i < length/2; i++) {
            if (chars[i] != chars[length-1-i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ThePalindromeOfString temp = new ThePalindromeOfString();
        boolean madam = temp.theStringIsPalindrome("123456543210");
        System.out.println(madam);
    }
}
