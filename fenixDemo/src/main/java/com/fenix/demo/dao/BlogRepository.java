package com.fenix.demo.dao;

import com.blinkfox.fenix.jpa.QueryFenix;
import com.fenix.demo.entity.Blog;
import com.fenix.demo.provider.SqlProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * 博客信息查询仓库.
 *
 * @author blinkfox on 2019-08-16.
 */
public interface BlogRepository extends JpaRepository<Blog, String> {


    /**
     * 使用 {@link QueryFenix} 注解和 Java API 来拼接 SQL 的方式来查询博客信息.
     *
     * @param blog 博客信息实体
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param blogIds 博客 ID 集合
     * @return 用户信息集合
     */
    @QueryFenix(provider = SqlProvider.class, method = "queryBlogsWithJava")
    List<Blog> queryBlogsWithJava(@Param("blog") Blog blog, @Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime, @Param("blogIds") String[] blogIds);
    /**
     * @Titel
     * @Description 联合查询
     * @Author  Kervin
     * @DateTime 2019/12/2 14:16
     * @return
     */
    @QueryFenix(provider = SqlProvider.class, method = "queryBlogsUnionUser")
    List<Object> queryBlogsUnionUser(@Param("blogId") String blogId);

}
