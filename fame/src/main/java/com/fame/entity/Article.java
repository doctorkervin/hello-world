package com.fame.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: hello-world
 * @desc: 博客文章实体类
 * @author: kervin
 * @time: 2019-12-20 10:48
 */

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//自增主键

    private String title; //文章标题

    private Long commentCount; //评论数

    @Version
    private Long version; //乐观锁 版本号
}
