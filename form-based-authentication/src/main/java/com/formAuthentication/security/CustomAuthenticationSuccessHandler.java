package com.formAuthentication.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

/**
 * In case if the user click on any link, want to redirect on the same link go for SavedRequestAwareAuthenticationSuccessHandler
 *
 * Spring Security already stores the request using a RequestCache the default implementation HttpSessionRequestCache
 * stores the last request in the HTTP session. You can access it using the SPRING_SECURITY_SAVED_REQUEST attribute
 * name to get it from the session.
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Optional<HttpSession> session = Optional.ofNullable(request.getSession());
        if(session.isPresent()){
            final SavedRequest savedRequest = (SavedRequest)session.get().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            String redirectUrl = savedRequest.getRedirectUrl();
            if(Objects.nonNull(redirectUrl)){
                log.info("Login success Handler Redirect URL: "+redirectUrl);
                // we do not forget to clean this attribute from session
                session.get().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
                // then we redirect
                getRedirectStrategy().sendRedirect(request, response, String.valueOf(redirectUrl));
            }else{
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }else{
            super.onAuthenticationSuccess(request, response, authentication);
        }


    }
}
