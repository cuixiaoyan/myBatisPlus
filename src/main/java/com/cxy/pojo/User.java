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