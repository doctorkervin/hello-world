package com.fenix.demo.dao;

import com.fenix.demo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Kervin
 * @ClassName com.fenix.demo.dao
 * @Description 用户dao
 * @Date 2019/11/29 18:06
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<Blog, String> {
}
