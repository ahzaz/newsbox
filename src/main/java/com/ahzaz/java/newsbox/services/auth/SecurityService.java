package com.ahzaz.java.newsbox.services.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ronakkhunt
 *         <p>
 *         REFER: http://docs.spring.io/spring-security/site/docs/3.0.x/reference/technical-overview.html
 *         YET TO REFER:
 *         http://docs.spring.io/spring-security/site/docs/3.0.x/reference/core-services.html
 *         <p>
 *         FOR REMEMBER-ME:
 *         http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 *         http://docs.spring.io/spring-security/site/docs/3.0.x/reference/remember-me.html
 */
@Service
public class SecurityService {

    public static Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Bean of this service's implementation is created in spring-security.xml
     */
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder encoder;

    public void login(String userName, String password) throws BadCredentialsException {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

            authenticationManager.authenticate(authenticationToken);

            if (authenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (UsernameNotFoundException e) {
            logger.info("Username not found : " + e.getMessage());
//            throw new BadCredentialsException(e.getMessage());
        }
        catch (BadCredentialsException e){
            logger.info("BadCred : " + e.getMessage());
//            throw e;
        }
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * Wrapper around {@link BCryptPasswordEncoder#encode(CharSequence)}.
     *
     * @param plainTextPassword
     * @return
     */
    public String encode(String plainTextPassword) {
        return encoder.encode(plainTextPassword);
    }

}
