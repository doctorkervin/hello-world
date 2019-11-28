package com.string;

/**
 * @Author Kervin
 * @ClassName com.string
 * @Description 测试jad
 * @Date 2019/11/28 10:10
 * @Version 1.0
 */
public class TestString {

    public static void main(String[] args) {
        // 无变量的字符串拼接
        String s = "aa"+"bb"+"dd";
        System.out.println(s);
        // 有变量的字符串拼接
        String g = "11"+s+5;
        System.out.println(g);
        // 循环中使用字符串拼接
        String a = "0";
        for (int i = 1; i < 10; i++) {
            a = a + i;
        }
        System.out.println(a);
        // 循环外定义StringBuilder
        StringBuilder b = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            b.append(i);
        }
        System.out.println(b);
    }
}
