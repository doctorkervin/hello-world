package com.fenix.demo;

import com.blinkfox.fenix.jpa.FenixJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author Kervin
 * @ClassName PACKAGE_NAME
 * @Description 启动主程序
 * @Date 2019/11/29 16:21
 * @Version 1.0
 */
@EnableJpaRepositories(repositoryFactoryBeanClass = FenixJpaRepositoryFactoryBean.class)
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class);
    }
}
