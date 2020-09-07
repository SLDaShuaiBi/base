package com.vaneqi.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author qinlei
 * @Date 2020/8/13
 * @Desc 登录接口返回给前端的数据
 */
@Data
public class UserAndRolesVO {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 加密密码
     */
    private String password;
    /**
     * 角色列表字符串
     */
    private List<String> roles;
    /**
     * 是否可用 0 可用；1 禁用
     */
    private String enable;
    /**
     * jwt token
     */
    private String token;
    /**
     * 拥有的权限列表
     */
    private List<String> permission;
}
