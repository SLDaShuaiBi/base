package com.vaneqi.mapper;

import com.vaneqi.entity.vo.UserAndRolesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qinlei
 * @Date 2020/8/13
 * @Desc 用户管理Mapper
 */
public interface UserMapperCustom {
    /**
     * 获取用户
     *
     * @param username
     * @return
     */
    UserAndRolesVO getUser(@Param("username") String username);

    /**
     * 获取多个角色权限
     *
     * @param userId
     * @return
     */
    List<String> listUserPermission(@Param("userId") Integer userId);

    /**
     * 获取所有权限
     *
     * @return
     */
    List<String> listPermission();
}