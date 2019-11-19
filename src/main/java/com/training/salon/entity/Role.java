package com.training.salon.entity;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MASTER, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
