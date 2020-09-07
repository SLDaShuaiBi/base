package com.vaneqi.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaneqi.common.Result;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qinlei
 * @Date 2020/7/31
 * @Desc 失败处理
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Result<Object> result = null;
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
        } else if (e instanceof InsufficientAuthenticationException) {
            result = Result.errorMsg("身份验证异常!");
        } else {
            result = Result.errorMsg("操作失败!");
        }
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
