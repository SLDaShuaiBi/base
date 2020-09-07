package com.vaneqi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaneqi.common.Result;
import com.vaneqi.config.JwtProperties;
import com.vaneqi.service.impl.AuthServiceImpl;
import com.vaneqi.utils.JwtTokenUtil;
import com.vaneqi.service.impl.UserServiceImpl;
import com.vaneqi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qinlei
 * @Date 2020/7/31
 * @Desc 登出成功处理
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String authHeader = httpServletRequest.getHeader(jwtProperties.getHeader());
        if (authHeader != null && authHeader.startsWith(jwtProperties.getHeaderPrefix())) {
            final String authToken = authHeader.substring(jwtProperties.getHeaderPrefix().length()).trim();
            Integer userId = jwtTokenUtil.getUserIdFromToken(authToken);
            System.out.println(userId);
            redisUtil.del(AuthServiceImpl.LOGIN_USER + userId);
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Result<Object> result = Result.ok("注销成功");
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
