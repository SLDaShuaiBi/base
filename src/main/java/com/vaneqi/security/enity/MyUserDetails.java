package com.vaneqi.security.enity;

import com.vaneqi.entity.vo.UserAndRolesVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author qinlei
 * @Date 2020/6/28
 */
public class MyUserDetails implements UserDetails {
    private UserAndRolesVO userAndRolesVO;

    public MyUserDetails(UserAndRolesVO user) {
        this.userAndRolesVO = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : userAndRolesVO.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userAndRolesVO.getPassword();
    }

    @Override
    public String getUsername() {
        return userAndRolesVO.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userAndRolesVO.getEnable().equals("0");
    }

    public Integer getUserId() {
        return userAndRolesVO.getId();
    }

    public UserAndRolesVO getUserAndRolesVO() {
        return userAndRolesVO;
    }
}
