package com.ahzaz.java.newsbox.services.auth;

import com.ahzaz.java.newsbox.model.AdminUser;
import com.ahzaz.java.newsbox.model.IUser;
import com.ahzaz.java.newsbox.model.User;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link UserDetailsService}.
 * <p>
 * Based on: https://hellokoding.com/registration-and-login-example-with-spring-xml-configuration-maven-jsp-and-mysql/
 * REFER: http://docs.spring.io/spring-security/site/docs/3.0.x/reference/technical-overview.html
 *
 * @author ronakkhunt
 */
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SecurityService securityService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        IUser adminUser = new AdminUser();
        IUser user = new User();
        if (adminUser.getUsername().equals(userName)) {
            adminUser.setPassword(securityService.encode(adminUser.getPassword()));
            return adminUser;
        }
        if (user.getUsername().equals(userName)) {
            throw new NotYetImplementedException();
//            return user;
        }
        throw new UsernameNotFoundException("Invalid Username or password");
    }

}
