package com.ahzaz.java.newsbox.admin.interceptors;

import com.ahzaz.java.newsbox.security.AdminSecure;
import com.ahzaz.java.newsbox.utils.SessionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ahzaz
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        SecurityContextHolder.getContext().setAuthentication((Authentication) session.getAttribute("auth"));
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).getBean() instanceof AdminSecure && !SessionUtils.isAdminLoggedIn()) {
                response.sendRedirect("/");
                return false;
            }
        }
        return true;
    }

    //http://stackoverflow.com/questions/16952718/springs-securitycontextholder-getcontext-getauthentication-returns-null-aft
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession(true);
        session.setAttribute("auth", SecurityContextHolder.getContext().getAuthentication());
    }
}
