package com.cxy.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: myBatisPlus
 * @description: mybatis配置类。
 * @author: cuixy
 * @create: 2020-08-04 11:21
 **/
@MapperScan("com.cxy.mapper")
@Configuration//配置类
@EnableTransactionManagement
public class MyBatisPlusConfig {
    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}