package com.fame.controller;

import com.fame.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hello-world
 * @desc: 评论controller层
 * @author: kervin
 * @time: 2019-12-20 10:56
 */
@Slf4j
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public String comment(Long articleId, String content) {
        try {
            commentService.postComment(articleId, content);
        } catch (Exception e) {
            log.error("{}", e);
            return "error: " + e.getMessage();
        }
        return "success";
    }
}
