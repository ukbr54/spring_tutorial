package com.formAuthentication.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Slf4j
public class AuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(!HttpMethod.POST.name().equals(request.getMethod())){
            log.info("Authentication Method Supported Only Http Post Method but recieving {}",request.getMethod());
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new AuthenticationServiceException("Username or Password not provided");
        }

        final Authentication auth = new UsernamePasswordAuthenticationToken(username,password);

        Authentication authenticate = getAuthenticationManager().authenticate(auth);

        return authenticate;
    }
}
