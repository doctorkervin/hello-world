package string.five;

import java.util.LinkedList;

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


    /**
     * 链表回文
     * 双向链表
     */
    boolean theLinkedListIsPalindrome(LinkedList<Character> list){
        int size = list.size();
        //处理like字符串数组一样
        //具体应该获取该链表的首节点和尾节点，
        //先判断首节点和尾节点的元素是否相同
        //相同则首节点获取后个节点，尾节点获取前节点，继续判断是否相同
        return true;
    }

    /**
     * 给定一个字符串，求他最长回文子串的长度
     * @param str
     * @return
     */
    String theLongestPalindromeOfString(String str){
        return "";
    }

    /**
     * manacher算法
     * @param str
     * @return
     * 例如String "abdfdg"
     * manacher "$a$b$d$f$d$g"
     */
    public char[] manacherString(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append("#");
            sb.append(str.charAt(i));
        }
        sb.append("#");
        return sb.toString().toCharArray();
    }

    /**
     * 最大回文长度
     * @param str
     * @return int
     */
    public int manacher(String str){
        if(str == null || str.length() < 1){
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] radius = new int[charArr.length];
        int R = -1;
        int c = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < radius.length; i++) {
            radius[i] = R > i ? Math.min(radius[2*c-i],R-i+1):1;
            while(i+radius[i] < charArr.length && i - radius[i] > -1){
                if(charArr[i-radius[i]] == charArr[i+radius[i]]){
                    radius[i]++;
                }else{
                    break;
                }
            }
            if(i + radius[i] > R){
                R = i + radius[i]-1;
                c = i;
            }
            max = Math.max(max,radius[i]);
        }
        return max-1;
    }

    public static void main(String[] args) {
        ThePalindromeOfString temp = new ThePalindromeOfString();
        boolean madam = temp.theStringIsPalindrome("123456543210");
        System.out.println(madam);
    }
}
