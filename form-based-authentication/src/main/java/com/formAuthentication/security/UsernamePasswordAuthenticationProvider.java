package com.formAuthentication.security;

import com.formAuthentication.persistence.model.User;
import com.formAuthentication.persistence.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private @Autowired BCryptPasswordEncoder encoder;
    private @Autowired UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication,"No Authentication data provided");
        String username = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));

        if(!encoder.matches(password,user.getPassword())){
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid");
        }

        if(CollectionUtils.isEmpty(user.getRoles())){
            throw new InsufficientAuthenticationException("User has no role assigned");
        }

        List<GrantedAuthority> authorities  =
                user.getRoles().stream().map(authority ->
                        new SimpleGrantedAuthority(user.getRoles().stream().map(auth -> auth.getRole().authority()).findAny().get()))
                        .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
