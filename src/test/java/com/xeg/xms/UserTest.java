package com.xeg.xms;

import com.xeg.xms.entity.User;
import com.xeg.xms.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUsersQuery(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
