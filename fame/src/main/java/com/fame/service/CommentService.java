package com.fame.service;

import com.fame.dao.ArticleRepository;
import com.fame.dao.CommentRepository;
import com.fame.entity.Article;
import com.fame.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @program: hello-world
 * @desc: 评论service
 * @author: kervin
 * @time: 2019-12-20 10:55
 */
@Slf4j
@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void postComment(Long articleId, String content) {
        //Optional<Article> articleOptional = articleRepository.findById(articleId);
        //Optional<Article> articleOptional = articleRepository.findArticleForUpdate(articleId);
        Optional<Article> articleOptional = articleRepository.findArticleWithPessimisticLock(articleId);
        if (!articleOptional.isPresent()) {
            throw new RuntimeException("没有对应的文章");
        }

        Article article = articleOptional.get();

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setContent(content);
        commentRepository.save(comment);

        int count = articleRepository.updateArticleWithVersion(article.getId(), article.getCommentCount() + 1, article.getVersion());
        if (count == 0) {
            throw new RuntimeException("服务器繁忙,更新数据失败");
        }
    }
}
