package com.cxy;

import com.cxy.mapper.UserMapper;
import com.cxy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: myBatisPlus
 * @description: 测试
 * @author: cuixy
 * @create: 2020-08-02 11:13
 **/
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contexLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


}