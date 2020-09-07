package com.vaneqi.service;

import com.vaneqi.entity.vo.UserAndRolesVO;

import java.util.List;

/**
 * @author qinlei
 * @Date 2020/8/28
 * @Desc 鉴权相关 service
 */
public interface AuthService {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    UserAndRolesVO login(String username, String password);

    /**
     * 查询角色对应权限
     *
     * @param userId 用户Id
     * @return 权限列表：用于判断界面权限和接口权限
     */
    List<String> listUserPermission(Integer userId);
}
