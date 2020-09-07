package com.vaneqi.service.impl;

import com.alibaba.fastjson.JSON;
import com.vaneqi.entity.vo.UserAndRolesVO;
import com.vaneqi.service.PermissionService;
import com.vaneqi.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qinlei
 * @Date 2020/8/28
 * @Desc
 */
@Service("ps")
public class PermissionServiceImpl implements PermissionService {
    public static final String ATTRIBUTES_USER_ID = "ATTRIBUTES_USER_ID";

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean hasPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer userId = (Integer) request.getAttribute(ATTRIBUTES_USER_ID);
        if (userId == null) {
            return false;
        }
        String userJson = redisUtil.get(AuthServiceImpl.LOGIN_USER + userId);
        if (userJson != null) {
            UserAndRolesVO userAndRolesVO = JSON.parseObject(userJson, UserAndRolesVO.class);
            return userAndRolesVO.getPermission().contains(permission);
        }
        return false;
    }
}
