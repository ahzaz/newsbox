package com.ahzaz.java.newsbox.model;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Ahzaz
 */
public interface IUser extends UserDetails{

    void setPassword(String password);

}
