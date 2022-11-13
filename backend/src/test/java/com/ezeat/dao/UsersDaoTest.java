package com.ezeat.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezeat.mapper.UsersMapper;
import com.ezeat.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTest {
    @Autowired
    private UsersMapper mapper;

    @Test
    void textRepeat(){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username","b");
        //根据条件查询一条数据，如果结果超过一条会报错
        Users users = mapper.selectOne(wrapper);
        System.out.println(users);
    }

    @Test
    void testQuery(){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username","zhangsan");
        wrapper.eq("password","123456");
        Users users = mapper.selectOne(wrapper);
        System.out.println(users);
    }

    @Test
    void testInsert(){
        Users users = new Users();
        users.setName("wangwu");
        users.setPassword("345678");
        int result = mapper.insert(users);
        System.out.println(result);
        System.out.println(users.getId());
    }

}
