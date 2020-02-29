package string.four;

/**
 * 字符串转成整型
 */
public class TheStringToInt {


    /**
     * 将一个由数字组成的字符串，转成整数并输出
     * 例如 "123" 输出 123
     *
     * 1，判断字符串为空，空抛错
     * 2， 如果字符串第一个字符是'-'，最终得倒的整数为负整数
     * 3，  该字符串不能包含不是数字的字符
     * 4，   字符串不能太长，否则换算成整数后，会导致整数溢出
     */
    private long theStringToInt(String str){
        //1，去空
        if (str.length() == 0){
            throw new RuntimeException("传入的字符串不能为空！");
        }

        //2，去除字符串的空格
        String temp = str.replace(" ", "");
        boolean positive = true; //表示是正整数
        if (temp.indexOf("-")>0){
            positive = false;
            temp = temp.substring(0);
        }
        char[] chars = temp.toCharArray();
        long sum = 0l;
        for (int i = 0; i < chars.length ; i++) {
            sum += Integer.valueOf(chars[i])*tenMutiplyTen(chars.length-i-1);
        }
        return positive?sum:-sum;
    }

    /**
     * n>=0 模拟阶乘
     * @param n
     * @return
     */
    private int tenMutiplyTen(int n){
        if (n == 0){
            return 1;
        }else{
            return 10*tenMutiplyTen(n-1);
        }
    }
}
