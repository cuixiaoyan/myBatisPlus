package com.cxy;

import com.cxy.mapper.UserMapper;
import com.cxy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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
        user.setAge(23);
        user.setEmail("56696508@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

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

    /**
     * 测试乐观锁失败
     */
    @Test
    public void testOptimisticLockerError() {
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




}