package com.fenix.demo.provider;

import com.blinkfox.fenix.bean.SqlInfo;
import com.blinkfox.fenix.core.Fenix;
import com.blinkfox.fenix.helper.CollectionHelper;
import com.blinkfox.fenix.helper.StringHelper;
import com.fenix.demo.entity.Blog;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Objects;

/**
 * @Author Kervin
 * @ClassName com.fenix.demo.provider
 * @Description 共有的sqlInfo
 * @Date 2019/12/2 13:35
 * @Version 1.0
 */
public class SqlProvider {
    /**
     * 通过 Java API 来拼接得到 {@link SqlInfo} 的方式来查询博客信息.
     *
     * @param blogIds 博客 ID 集合
     * @param blog 博客信息实体
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return {@link SqlInfo} 示例
     */
    public SqlInfo queryBlogsWithJava(@Param("blogIds") String[] blogIds, @Param("blog") Blog blog,
                                      @Param("startTime") Date startTime, @Param("endTime") Date endTime) {
        return Fenix.start()
                .select("b")
                .from("Blog").as("b")
                .where()
                .in("b.id", blogIds, CollectionHelper.isNotEmpty(blogIds))
                .andLike("b.title", blog.getTitle(), StringHelper.isNotBlank(blog.getTitle()))
                .andLike("b.author", blog.getAuthor(), StringHelper.isNotBlank(blog.getAuthor()))
                .andBetween("b.createTime", startTime, endTime, startTime != null || endTime != null)
                .end();
    }

    public SqlInfo queryBlogsUnionUser(@Param("blogIds") String blogId){
        return Fenix.start()
                .select("b.id, b.title, b.author, u.name")
                .from("Blog").as("b")
                .leftJoin("User").as("u")
                .on("b.userId = u.id")
                .where("1=1")
                .andEqual("b.id", blogId )
                .end();
    }
}
