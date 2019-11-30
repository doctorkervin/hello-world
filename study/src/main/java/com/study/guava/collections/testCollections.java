package com.study.guava.collections;

import com.google.common.collect.ImmutableList;

/**
 * @Author Kervin
 * @ClassName com.study.guava.collections
 * @Description 测试guava集合
 * @Date 2019/11/30 10:37
 * @Version 1.0
 */
public class testCollections {

    private void test01(){
        //不可变得list集合,不能添加和修改,删除,是线程安全的
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");
    }

}
