package com.fame.dao;

import com.fame.entity.Article;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @program: hello-world
 * @desc: 文章接口类
 * @author: kervin
 * @time: 2019-12-20 10:52
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Query(value = "select * from article a where a.id = :id for update", nativeQuery = true)
    Optional<Article> findArticleForUpdate(@Param("id") Long id);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Article a where a.id = :id")
    Optional<Article> findArticleWithPessimisticLock(@Param("id") Long id);

    @Modifying
    @Query(value = "update article set comment_count = :commentCount, version = version + 1 where id = :id and version = :version", nativeQuery = true)
    int updateArticleWithVersion(@Param("id") Long id, @Param("commentCount") Long commentCount, @Param("version") Long version);
}
