# MyBatisPlus概述

需要的基础：把我的MyBatis、Spring、SpringMVC就可以学习这个了！
为什么要学习它呢？MyBatisPlus可以节省我们大量工作时间，所有的CRUD代码它都可以自动化完成！
JPA 、 tk-mapper、MyBatisPlus
偷懒的！

> 简介

是什么？ MyBatis 本来就是简化 JDBC 操作的！
官网：https://mp.baomidou.com/ MyBatis Plus，简化 MyBatis ！

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200802103812370.png" alt="image-20200802103812370" style="zoom:50%;" />

> 特性

- 无侵入：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- 损耗小：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作， BaseMapper
- 强大的 CRUD 操作：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分
  CRUD 操作，更有强大的条件构造器，满足各类使用需求, 以后简单的CRUD操作，它不用自己编写
  了！
- 支持 Lambda 形式调用：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
  支持主键自动生成：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配
  置，完美解决主键问题
- 支持 ActiveRecord 模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大
  的 CRUD 操作
- 支持自定义全局通用操作：支持全局通用方法注入（ Write once, use anywhere ）
- 内置代码生成器：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、
  Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用（自动帮你生成代码）
- 内置分页插件：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同
  于普通 List 查询
- 分页插件支持多种数据库：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、
  Postgre、SQLServer 等多种数据库
- 内置性能分析插件：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢
  查询
- 内置全局拦截插件：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误
  操作

# 快速入门

地址：https://mp.baomidou.com/guide/quick-start.html#初始化工程
使用第三方组件：
1、导入对应的依赖
2、研究依赖如何配置
3、代码如何编写
4、提高扩展技术能力！

> 步骤

1、创建数据库 mybatis_plus
2、创建user表

```sql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200802104359699.png" alt="image-20200802104359699" style="zoom:50%;" />

3、编写项目，初始化项目！使用SpringBoot初始化！
4、导入依赖

```yaml
 		testCompile group: 'junit', name: 'junit', version: '4.12'
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    compile group: 'mysql', name: 'mysql-connector-java', version: '8.0.21'

    annotationProcessor 'org.projectlombok:lombok:1.18.2'
    compileOnly 'org.projectlombok:lombok:1.18.2'

    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter
    compile group: 'com.baomidou', name: 'mybatis-plus-boot-starter', version: '3.3.2'
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.11.1'

```

说明：我们使用 mybatis-plus 可以节省我们大量的代码，尽量不要同时导入 mybatis 和 mybatis
plus！版本的差异！
5、连接数据库！这一步和 mybatis 相同！

```yaml
# mysql 5 驱动不同 com.mysql.jdbc.Driver
# mysql 8 驱动不同com.mysql.cj.jdbc.Driver、需要增加时区的配置serverTimezone=GMT%2B8
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/mybatisplus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
```

6、传统方式pojo-dao（连接mybatis，配置mapper.xml文件）-service-controller
7、使用了mybatis-plus 之后

- pojo

```java
package com.cxy.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @program: myBatisPlus
 * @description: 用户类
 * @author: cuixy
 * @create: 2020-08-02 11:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

- mapper接口

```java
package com.cxy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import User;
// 在对应的Mapper上面继承基本的类 BaseMapper
@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {
    // 所有的CRUD操作都已经编写完成了
    // 你不需要像以前的配置一大堆文件了！
}
```

- 注意点，我们需要在主启动类上去扫描我们的mapper包下的所有接口
  @MapperScan("com.cxy.mapper")

```java
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
//暂时加在这里。
@MapperScan("com.cxy.mapper")
public class myBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(myBatisPlusApplication.class, args);
    }
}
```



- 测试类中测试，注意路径。

![image-20200802115528838](https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200802115528838.png)

# 配置日志

