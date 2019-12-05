package com.study.guava.collections;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import java.util.List;

/**
 * @Author Kervin
 * @ClassName com.study.guava.collections
 * @Description 测试集合的去重与过滤
 * @Date 2019/12/5 10:23
 * @Version 1.0
 */
public class testLists {
    private List<String> lists =Lists.newArrayList("a","b","c","a","b","a","d");

    @Test
    public void testremoveDuplicate(){
        ImmutableList<String> strings = ImmutableSet.copyOf(lists).asList();
        strings.stream().forEach(System.out::println);
    }

    @Test
    public void testfilter(){
        Collections2.filter(lists, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String s) {

                if ("a".equalsIgnoreCase(s)) {
                    return false;
                } else {
                    return true;
                }
            }
        }).stream().forEach(System.out::println);

    }
}
