package string.homework;

import java.util.*;

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

    /**
     * 使用lambda表达式进行转换，排序，和限定输出
     *
     */
    private List<String> perform(){
        List<PlaceOrder> list = new ArrayList<>();
        PlaceOrder placeOrder1 = new PlaceOrder();
        PlaceOrder placeOrder2 = new PlaceOrder();
        placeOrder1.setOrderNo("12132312312312");
        placeOrder1.setOrderTime(new Date());
        placeOrder2.setOrderNo("343434332342433");
        list.add(placeOrder1);
        Date date = new Date(2020, 3, 6);
        placeOrder2.setOrderTime(date);
        list.add(placeOrder2);
        list.stream().sorted(new Comparator<PlaceOrder>() {
            @Override
            public int compare(PlaceOrder o1, PlaceOrder o2) {
                return o2.getOrderTime().compareTo(o1.getOrderTime());
            }
        }).map(placeOrder -> placeOrder.getOrderNo()).limit(1).forEach(System.out::println);
        return null;

    }
    static class PlaceOrder{
        private Date orderTime;
        private String orderNo;

        public Date getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Date orderTime) {
            this.orderTime = orderTime;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
    }

    public static void main(String[] args) {
        TheHomeWorkOfString th = new TheHomeWorkOfString();
        th.perform();
    }
}

