package com.cxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: myBatisPlus
 * @description: 主启动类
 * @author: cuixy
 * @create: 2020-08-02 11:23
 **/
@SpringBootApplication
@MapperScan("com.cxy.mapper")
public class myBatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(myBatisPlusApplication.class, args);
    }


}