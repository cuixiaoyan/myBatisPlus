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

    /**
     * 查询列表
     */
    @Test
    public void contexLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("cuixiaoyan333");
        user.setAge(33);
        user.setEmail("56696508@qq.com");

        //自动生成id
        int insert = userMapper.insert(user);
        //受影响的行数。
        System.out.println(insert);
        System.out.println(user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1290207991672348674L);
        user.setName("cuixiaoyan333");
        user.setAge(33);
        user.setEmail("56696508@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }


}