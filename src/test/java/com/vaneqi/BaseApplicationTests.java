package com.vaneqi;

import com.alibaba.fastjson.JSON;
import com.vaneqi.entity.vo.UserAndRolesVO;
import com.vaneqi.service.UserService;
import com.vaneqi.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootTest
class BaseApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Test
    void set() {
        UserAndRolesVO userAndRolesVO = new UserAndRolesVO();
        userAndRolesVO.setId(1);
        userAndRolesVO.setPassword("123456");
        userAndRolesVO.setToken("token");
        userAndRolesVO.setUserName("username");
        userAndRolesVO.setEnable("0");
        userAndRolesVO.setRoles(new ArrayList<>());

        redisUtil.set("user", JSON.toJSONString(userAndRolesVO), 10000);
    }

    @Test
    void get() {
        redisUtil.set("user", "JSON.toJSONString(userAndRolesVO)", 50000);
    }

    @Test
    void del() {
        redisUtil.expire("user", 50000);
    }

}
