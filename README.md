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

我们所有的sql现在是不可见的，我们希望知道它是怎么执行的，所以我们必须要看日志！

```yaml
# 配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

![image-20200803163851544](https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803163851544.png)

配置完毕日志之后，后面的学习就需要注意这个自动生成的SQL。

# CRUD扩展

## 插入操作

> Insert插入

```java
 /**
     * 测试插入
     */
    @Test
    public void testInsert() {
        User user = new User(null, "cuixiaoyan", 3, "56696508@qq.com");
        //自动生成id
        int insert = userMapper.insert(user);
        //受影响的行数。
        System.out.println(insert);
        System.out.println(user);
    }
```

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803165630366.png" alt="image-20200803165630366" style="zoom:50%;" />

>数据库插入的id的默认值为：全局的唯一id。

## 主键生成策略

>默认 ID_WORKER 全局唯一id

分布式系统唯一id生成：https://www.cnblogs.com/haoxinyue/p/5208136.html

### 雪花算法

snowflake是Twitter开源的分布式ID生成算法，结果是一个long型的ID。其核心思想是：使用41bit作为
毫秒数，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），12bit作为毫秒内的流水号（意味
着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是0。可以保证几乎全球唯
一！

### 主键自增

我们需要配置主键自增：

1、实体类字段上 @TableId(type = IdType.AUTO)
2、数据库字段一定要是自增！

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803170120270.png" alt="image-20200803170120270" style="zoom:50%;" />

3、再次测试插入即可！

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803170542623.png" alt="image-20200803170542623" style="zoom:50%;" />

```java
/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.annotation;

import lombok.Getter;

/**
 * 生成ID类型枚举类
 *
 * @author hubin
 * @since 2015-11-10
 */
@Getter
public enum IdType {
    /**
     * 数据库ID自增
     */
    AUTO(0),
    /**
     * 该类型为未设置主键类型(注解里等于跟随全局,全局里约等于 INPUT)
     */
    NONE(1),
    /**
     * 用户输入ID
     * <p>该类型可以通过自己注册自动填充插件进行填充</p>
     */
    INPUT(2),

    /* 以下3种类型、只有当插入对象ID 为空，才自动填充。 */
    /**
     * 分配ID (主键类型为number或string）,
     * 默认实现类 {@link com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator}(雪花算法)
     *
     * @since 3.3.0
     */
    ASSIGN_ID(3),
    /**
     * 分配UUID (主键类型为 string)
     * 默认实现类 {@link com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator}(UUID.replace("-",""))
     */
    ASSIGN_UUID(4),
    /**
     * @deprecated 3.3.0 please use {@link #ASSIGN_ID}
     */
    @Deprecated
    ID_WORKER(3),
    /**
     * @deprecated 3.3.0 please use {@link #ASSIGN_ID}
     */
    @Deprecated
    ID_WORKER_STR(3),
    /**
     * @deprecated 3.3.0 please use {@link #ASSIGN_UUID}
     */
    @Deprecated
    UUID(4);

    private final int key;

    IdType(int key) {
        this.key = key;
    }
}

```

## 更新操作

```java
		/**
     * 测试更新
     * updateById居然是要传入一个对象。
     */
    @Test
    public void testUpdate() {
        User user = new User(1290207991672348674L, "cuixiaoyan222", 18, "cxy@11.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
```



<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803171451479.png" alt="image-20200803171451479" style="zoom:50%;" />

## 自动填充

创建时间、修改时间！这些个操作一遍都是自动化完成的，我们不希望手动更新！
阿里巴巴开发手册：所有的数据库表：gmt_create、gmt_modified几乎所有的表都要配置上！而且需
要自动化！

>方式一：数据库级别（工作中不允许你修改数据库）

1、在表中新增字段 create_time, update_time

2、再次测试插入方法，我们需要先把实体类同步！ ==CURRENT_TIMESTAMP==

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803172213947.png" alt="image-20200803172213947" style="zoom:50%;" />

private Date createTime;
private Date updateTime;

3、再次更新查看结果即可

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200803172527304.png" alt="image-20200803172527304" style="zoom:50%;" />

>方式二：代码级别

1、删除数据库的默认值、更新操作！

2、实体类字段属性上需要增加注解

```java
		@TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
```

3、编写处理器来处理这个注解即可！

```java
package com.cxy.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: myBatisPlus
 * @description: mybatis控制器
 * @author: cuixy
 * @create: 2020-08-03 17:29
 **/
@Slf4j
@Component //一定不要忘记把处理器加到IOC容器中！
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill.....");
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
    // 修改时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill.....");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
```

4、测试插入
5、测试更新、观察时间即可！

## 乐观锁

乐观锁 : 故名思意十分乐观，它总是认为不会出现问题，无论干什么不去上锁！如果出现了问题，
再次更新值测试
悲观锁：故名思意十分悲观，它总是认为总是出现问题，无论干什么都会上锁！再去操作！

我们这里主要讲解 乐观锁机制！
乐观锁实现方式：

- 取出记录时，获取当前 version
- 更新时，带上这个version
- 执行更新时， set version = newVersion where version = oldVersion
- 如果version不对，就更新失败

```sql
乐观锁：1、先查询，获得版本号 version = 1
-- A
update user set name ="cuixiaoyan", version = version + 1
where id = 2 and version = 1
-- B 线程抢先完成，这个时候 version = 2，会导致 A 修改失败！
update user set name ="cuixiaoyan", version = version + 1
where id = 2 and version = 1
```

>测试一下MP的乐观锁插件

1、给数据库中增加version字段！类型为int默认值为1。

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200804111823824.png" alt="image-20200804111823824" style="zoom:50%;" />

2、我们实体类加对应的字段

```java
@Version //乐观锁Version注解
private Integer version;
```

3、注册组件

```java
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
```

4、测试一下！

```java
 /**
     * 测试乐观锁成功
     */
    @Test
    public void testOptimisticLockerSuccess() {
        User user = userMapper.selectById(1L);
        user.setName("cuxiaoyan444");
        user.setEmail("22@1.com");
        userMapper.updateById(user);
    }
```

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200804113057166.png" alt="image-20200804113057166" style="zoom:50%;" />

5、失败测试

```java
/**
     * 测试乐观锁失败
     */
    @Test
    public void testOptimisticLockerError(){
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("cuxiaoyan555");
        user.setEmail("22@1.com");
        //线程2
        User user1 = userMapper.selectById(1L);
        user1.setName("cuxiaoyan666");
        user1.setEmail("22@1.com");
        //插队操作，执行成功。
        userMapper.updateById(user1);
        //则失败。
        userMapper.updateById(user);
    }
```

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200804113800016.png" alt="image-20200804113800016" style="zoom:50%;" />

## 查询操作

```java
/**
     * 测试查询
     */
    @Test
    public void testSelectById() {
        System.out.println(userMapper.selectById(1L));
    }

    //测试批量查询
    @Test
    public void testSelectByBatchId() {
        userMapper.selectBatchIds(Arrays.asList(1, 2, 3)).forEach(System.out::println);
    }

    //按条件查询之一，使用map操作
    @Test
    public void testSelectByBatchIds() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "cuxiaoyan666");
        map.put("age", 18);
        userMapper.selectByMap(map).forEach(System.out::println);
    }

```

## 分页查询

1、原始的 limit 进行分页
2、pageHelper 第三方插件
3、MP 其实也内置了分页插件！

> 如何使用？

1、配置拦截器组件即可

```java
 //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
```

2、直接使用Page对象即可！

```java
 //分页测试
    @Test
    public void testPage() {
        // 参数一：当前页
        // 参数二：页面大小
        // 使用了分页插件之后，所有的分页操作也变得简单的！
        Page<User> userPage = new Page<>(1, 2);
        userMapper.selectPage(userPage, null);
        userPage.getRecords().forEach(System.out::println);
        System.out.println(userPage.getTotal());

    }
```

<img src="https://gitee.com/cuixiaoyan/uPic/raw/master/uPic/image-20200805165506537.png" alt="image-20200805165506537" style="zoom:50%;" />

## 删除操作

1、根据 id 删除记录,物理删除，也就是直接删除记录。

```java
//测试删除，通过id删除
    @Test
    public void testDeleteById() {
        System.out.println(userMapper.deleteById(1L));
    }

    //通过id批量删除
    @Test
    public void testDeleteBatchId() {
        System.out.println(userMapper.deleteBatchIds(Arrays.asList(2L, 3L)));
    }

    //通过map删除
    @Test
    public void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "cuixiaoyan111");
        System.out.println(userMapper.deleteByMap(map));

    }
```

## 逻辑删除

