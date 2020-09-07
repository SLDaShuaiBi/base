package com.vaneqi.utils;

import com.vaneqi.config.JwtProperties;
import com.vaneqi.security.enity.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinlei
 * @Date 2020/8/14
 * @Desc
 */
@Component
public class JwtTokenUtil {

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param myUserDetails
     * @return 令牌
     */
    public String generateToken(MyUserDetails myUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", myUserDetails.getUsername());
        claims.put("userid", myUserDetails.getUserId());
        claims.put("created", new Date());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从令牌中获取用户id
     *
     * @param token 令牌
     * @return 用户名
     */
    public Integer getUserIdFromToken(String token) {
        Integer userid;
        try {
            Claims claims = getClaimsFromToken(token);
            userid = (Integer) claims.get("userid");
        } catch (Exception e) {
            userid = null;
        }
        return userid;
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            e.printStackTrace();
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token 令牌
     * @param
     * @return 是否有效
     */
    public Boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public void refreshTokenExpiration(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date loginDate = claims.getExpiration();

        } catch (Exception e) {
        }
    }
}
