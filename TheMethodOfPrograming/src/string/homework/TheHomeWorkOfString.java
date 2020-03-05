package string.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: doctor kervin
 * @date: 2020-02-27 22:09
 **/
public class TheHomeWorkOfString {
    /**
     * 字符串反转
     */
    private String revertOfString(String str){
        char[] chars = str.toCharArray();
        for (int l = 0, r = chars.length - 1; l < r; l++, r--) {
            Character temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
        }
        return new String(chars);
    }

    /**
     * 字符串的左右移动
     * 字符串为*和26个字母随机组成，现在需要将*号左移动，字母向右移动且顺序不变
     */
    private String moveOfString(String str){
        char[] chars = str.toCharArray();
        int t = chars.length-1;
        for (int i = 0; i < t; i++) {
            if (chars[i] != '*'){
                for (int j = t; j > i ; j--) {
                    if (chars[j] == '*'){
                        Character temp = chars[j];
                        chars[j] = chars[i];
                        chars[i] = temp;
                        t = j;
                        //跳出整个循环
                        break;
                    }else {
                        //跳过本次循环，继续下次循环
                        continue;
                    }
                }
            }else {
                continue;
            }
        }
        return new String(chars);
    }


    /**
     * 字符个数的统计
     * 给定一个字符串，统计各个字符串的个数，区分大小写，时间复杂度为O(n)
     * O(n)
     */
    private Map<Character,Integer> countEachCharacterOfString(String str){
       Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i],map.get(chars[i])+1);
        }
        return map;
    }

    /**
     * 字符串的匹配
     */



    /**
     * 字符串空格的压缩
     */

}
