package com.fame.dao;

import com.fame.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: hello-world
 * @desc: 评论dao层
 * @author: kervin
 * @time: 2019-12-20 10:53
 */
public interface CommentRepository  extends CrudRepository<Comment, Long> {
    @Modifying
    @Query("delete from Comment c where c.id = ?1")
    @Transactional
    void deleteInBulkById(long id);
}
