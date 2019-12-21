package com.fame.dao;

import com.fame.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * @program: hello-world
 * @desc: 评论dao层
 * @author: kervin
 * @time: 2019-12-20 10:53
 */
public interface CommentRepository  extends CrudRepository<Comment, Long> {
}
