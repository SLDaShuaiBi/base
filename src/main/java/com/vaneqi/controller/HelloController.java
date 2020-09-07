package com.vaneqi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinlei
 * @Date 2020/8/13
 */
@Api(value = "测试接口", tags = {"测试接口"})
@RestController
@RequestMapping("hello")
public class HelloController {

    @ApiOperation(value = "say", notes = "say")
    @PreAuthorize("@ps.hasPermission('workshop')")
    @GetMapping("say")
    public String say() {
        return "say_hello";
    }
}
