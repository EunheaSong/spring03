package com.board.spring03.security;


import com.board.spring03.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //계정 만료됐니?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정 잠겼니?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //계정 암호 오래됐니?
        return true;
    }

    @Override
    public boolean isEnabled() { //계정이 활성화 됐니?
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}