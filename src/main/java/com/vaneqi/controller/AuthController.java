package com.vaneqi.controller;

import com.vaneqi.common.Result;
import com.vaneqi.entity.vo.UserAndRolesVO;
import com.vaneqi.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vaneqi.security.handler.MyLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qinlei
 * @Date 2020/8/19
 * @Desc
 */
@Api(value = "鉴权", tags = {"鉴权相关接口"})
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, example = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, example = "123456")
    })
    @PostMapping("login")
    public Result<UserAndRolesVO> login(String username, String password) {
        UserAndRolesVO userAndRolesVO = authService.login(username, password);
        userAndRolesVO.setPassword(null);
        return Result.ok(userAndRolesVO);
    }

    /**
     * 只用作 swagger 显示，功能由 security 拦截器完成
     *
     * @see MyLogoutSuccessHandler#onLogoutSuccess(HttpServletRequest, HttpServletResponse, Authentication)
     */
    @ApiOperation(value = "退出接口", notes = "退出接口")
    @GetMapping("logout")
    public Result<String> logout() {
        return Result.ok();
    }
}
