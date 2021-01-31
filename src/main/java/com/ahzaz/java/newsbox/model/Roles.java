package com.ahzaz.java.newsbox.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author Ahzaz
 */
public class Roles {
    public static final GrantedAuthority ADMIN_ROLE = new SimpleGrantedAuthority("ROLE_ADMIN");
}
