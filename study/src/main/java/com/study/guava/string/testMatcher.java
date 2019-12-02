package com.study.guava.string;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * @Author Kervin
 * @ClassName com.study.guava.string
 * @Description 测试字符串匹配
 * @Date 2019/12/2 11:15
 * @Version 1.0
 */
public class testMatcher {
    @Test
    public void test01(){
        String string = "123AWEEEdfdf34";
        //CharMatcher.javaIsoControl().removeFrom(string);
        String s = CharMatcher.javaDigit().or(CharMatcher.javaIsoControl()).removeFrom(string);//剔除数字
        System.out.println(s);
    }
}
