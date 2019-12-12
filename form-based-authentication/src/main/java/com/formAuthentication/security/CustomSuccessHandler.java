package com.formAuthentication.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

/**
 * Redirect to Different Pages after Login with Spring Security
 *  Sometimes its required to redirect user to different pages post login based on the role of the user.For example
 *  if an user has an USER role then we want him to be redirected to /user and similarly to /admin for users having ADMIN role.
 *  In this post, we will be discussing about how to redirect user to different pages post login based on the role of the user.
 *  We will be implementing AuthenticationSuccessHandler of spring boot security to implement our custom way of redirecting user
 *  to different pages after successful login.
 */
@Slf4j
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final String URL = "/";
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(response.isCommitted()) {
            log.info("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request,response,URL);
    }
}
