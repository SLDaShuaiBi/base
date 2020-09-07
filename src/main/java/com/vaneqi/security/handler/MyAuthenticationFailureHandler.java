package com.vaneqi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaneqi.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qinlei
 * @Date 2020/7/31
 * @Desc 登录失败处理
 */
@Deprecated
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Result<Object> result;
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = Result.errorMsg(e.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            result = Result.errorMsg("用户或账号密码错误");
        } else if (e instanceof LockedException) {
            result = Result.errorMsg("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            result = Result.errorMsg("证书过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            result = Result.errorMsg("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            result = Result.errorMsg("账户被禁用，请联系管理员!");
        } else {
            log.error("登录失败：", e);
            result = Result.errorMsg("登录失败!");
        }
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
