package com.example.marketdota.model;

import org.springframework.security.core.GrantedAuthority;

public enum roleEnum  implements GrantedAuthority {
    USER, ADMIN, EMPLOYEE;

    @Override
    public String getAuthority()
    {
        return name();
    }
}
