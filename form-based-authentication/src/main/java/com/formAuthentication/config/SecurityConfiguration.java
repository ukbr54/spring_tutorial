package com.formAuthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
           .inMemoryAuthentication()
              .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
           .and()
              .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
           .and()
              .withUser("manager").password(passwordEncoder().encode("manager123")).roles("MANAGER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .authorizeRequests()
              .antMatchers("/index.html","/login/**").permitAll()
              .antMatchers("/profile/**").authenticated()
              .antMatchers("/admin/**").hasRole("ADMIN")
              .antMatchers("/management/**").hasAnyRole("ADMIN","MANAGER")
           .and()
              .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .failureUrl("/login?error=true");
    }
}
