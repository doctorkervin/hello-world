package com.study.guava.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Kervin
 * @ClassName com.study.guava.string
 * @Description 测试String
 * @Date 2019/11/30 10:50
 * @Version 1.0
 */
public class testString {

    // 将String转为map
    @Test
    public void test01() {
        String postbody = "a=a&b=b&c=c";
        Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split(postbody);
        System.out.println(map.get("a"));
    }

    //将Map转为String 22ms
    //注意map里面的key对应的值不能为null，否则Joiner会报空指针异常
    @Test
    public void mapToString() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("a", "hah");
        map.put("b", "xixi");
        String response = Joiner.on("&").withKeyValueSeparator("=").join(map);
        String join = Joiner.on("").join(response, "&", "key", "=", "keyvalue");
        System.out.println(join);
    }

    private String getTreeMapToString(Map<String, String> treeMap, String keyName, String code) {
        StringBuilder sb = new StringBuilder();
        treeMap.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(String.valueOf(v)) && !"null".equals(String.valueOf(v))) {
                sb.append(k).append("=").append(v).append("&");
            }
        });
        sb.append(keyName).append("=").append(code);
        return sb.toString();
    }

    //79ms
    @Test
    public void testMapToStringSpeed() {
        Map<String, String> map = Maps.newTreeMap();
        map.put("a", "hah");
        map.put("b", "xixi");
        String treeMapToString = getTreeMapToString(map, "key", "sdert");
        System.out.println(treeMapToString);
    }

    //有序的hashmap,耗时:54ms
    @Test
    public void testOrderMapToString(){
        ImmutableSortedMap.Builder<String, String> b2 = ImmutableSortedMap.naturalOrder();
        b2.put("t","hah");
        b2.put("c","xixi");
        b2.put("key","sdert");
        ImmutableSortedMap<String, String> map = b2.build();
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map));
    }


}
