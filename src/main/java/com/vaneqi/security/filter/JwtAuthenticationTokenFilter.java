package com.vaneqi.security.filter;

import com.alibaba.fastjson.JSON;
import com.vaneqi.entity.vo.UserAndRolesVO;
import com.vaneqi.config.JwtProperties;
import com.vaneqi.security.enity.MyUserDetails;
import com.vaneqi.service.impl.AuthServiceImpl;
import com.vaneqi.service.impl.PermissionServiceImpl;
import com.vaneqi.utils.JwtTokenUtil;
import com.vaneqi.service.impl.UserServiceImpl;
import com.vaneqi.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qinlei
 * @Date 2020/8/14
 * @Desc
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(jwtProperties.getHeader());
        if (authHeader != null && authHeader.startsWith(jwtProperties.getHeaderPrefix())) {
            final String authToken = authHeader.substring(jwtProperties.getHeaderPrefix().length()).trim();
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            Integer userId = jwtTokenUtil.getUserIdFromToken(authToken);
            logger.info("checking authentication " + username + ":" + userId);
            request.setAttribute(PermissionServiceImpl.ATTRIBUTES_USER_ID, userId);
            String userJson = redisUtil.get(AuthServiceImpl.LOGIN_USER + userId);
            if (userJson != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserAndRolesVO userAndRolesVO = JSON.parseObject(userJson, UserAndRolesVO.class);
                UserDetails userDetails = new MyUserDetails(userAndRolesVO);
                if (authToken.equals(userAndRolesVO.getToken()) && jwtTokenUtil.validateToken(authToken)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //延长用户认证有效期时间
                    redisUtil.expire(AuthServiceImpl.LOGIN_USER + userId, jwtProperties.getExpiration());
                }
            }
        }
        chain.doFilter(request, response);
    }
}
