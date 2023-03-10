package com.baizhi.test;

import com.baizhi.SpringbootRedisApplication;
import com.baizhi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringbootRedisApplication.class)
public class TestService {
    @Autowired
    private UserService userService;
    @Test
    public void test(){
        userService.findAll().forEach(user -> System.out.println("user = " + user));
    }
}
