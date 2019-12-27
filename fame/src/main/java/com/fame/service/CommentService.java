package com.fame.service;

import com.fame.dblock.annotation.RetryOnFailure;
import com.fame.common.ApiResultEnum;
import com.fame.dao.ArticleRepository;
import com.fame.dao.CommentRepository;
import com.fame.entity.Article;
import com.fame.entity.Comment;
import com.fame.exception.TryAgainException;
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

    @RetryOnFailure
    @Transactional(rollbackFor = Exception.class)
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
            //如果更新失败就抛出去，重试
            throw new TryAgainException(ApiResultEnum.ERROR_TRY_AGAIN);
        }
    }
}
