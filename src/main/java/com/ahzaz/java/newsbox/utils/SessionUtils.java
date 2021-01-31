package com.ahzaz.java.newsbox.utils;

import com.ahzaz.java.newsbox.model.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Ahzaz
 */
public class SessionUtils {


    public static boolean isAdminLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().contains(Roles.ADMIN_ROLE);
    }

}
