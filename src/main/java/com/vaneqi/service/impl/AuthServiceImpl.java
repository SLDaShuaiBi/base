package com.vaneqi.service.impl;

import com.alibaba.fastjson.JSON;
import com.vaneqi.config.JwtProperties;
import com.vaneqi.entity.vo.UserAndRolesVO;
import com.vaneqi.mapper.UserMapperCustom;
import com.vaneqi.security.enity.MyUserDetails;
import com.vaneqi.service.AuthService;
import com.vaneqi.utils.JwtTokenUtil;
import com.vaneqi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qinlei
 * @Date 2020/8/28
 * @Desc
 */
@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    public static final String LOGIN_USER = "LOGIN_USER:";

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    UserMapperCustom userMapperCustom;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAndRolesVO user = userMapperCustom.getUser(username);
        if (user == null) {
            return null;
        } else {
            return new MyUserDetails(user);
        }
    }

    @Override
    public UserAndRolesVO login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        //保存用户信息到 redis 中
        String token = jwtTokenUtil.generateToken(userDetails);
        userDetails.getUserAndRolesVO().setToken(token);
        List<String> permissions = listUserPermission(userDetails.getUserId());
        userDetails.getUserAndRolesVO().setPermission(permissions);
        redisUtil.set(LOGIN_USER + userDetails.getUserId(), JSON.toJSONString(userDetails.getUserAndRolesVO()), jwtProperties.getExpiration());
        return userDetails.getUserAndRolesVO();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<String> listUserPermission(Integer userId) {
        List<String> permissions = new ArrayList<>();
        if (userId == 1) {
            permissions = userMapperCustom.listPermission();
        } else {
            permissions = userMapperCustom.listUserPermission(userId);
        }
        return permissions;
    }
}
