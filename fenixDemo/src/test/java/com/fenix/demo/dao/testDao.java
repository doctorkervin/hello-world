package com.fenix.demo.dao;

import com.fenix.demo.entity.Blog;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @Author Kervin
 * @ClassName com.fenix.demo.dao
 * @Description 测试Dao
 * @Date 2019/12/2 13:38
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testDao {
    @Resource
    private BlogRepository blogRepository;

    /**
     * 测试使用 {@QueryFenix} 注解和 Java API 来拼接 SQL 的方式来查询博客信息.
     */
    @Test
    public void queryBlogsWithJava() {
        // 构造查询的相关参数.
        String[] ids = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        Blog blog = new Blog().setAuthor("ZhangSan");
        Date startTime = Date.from(LocalDateTime.of(2019, Month.APRIL, 8, 0, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(LocalDateTime.of(2019, Month.OCTOBER, 8, 0, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant());

        // 查询并断言查询结果的正确性.
        List<Blog> blogs = blogRepository.queryBlogsWithJava(blog, startTime, endTime, ids);
        System.out.println(blogs.toString());
    }

    /**
     * 测试使用 {@QueryFenix} 注解和 Java API 来拼接 SQL 的方式来查询博客信息.
     */
    @Test
    public void queryBlogsUnionUser() {
        // 构造查询的相关参数.
        String id = "1";

        // 查询并断言查询结果的正确性.
        List<Object> lists = blogRepository.queryBlogsUnionUser(id);
        System.out.println(lists.toString());
    }
}
