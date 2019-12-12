package com.formAuthentication.config;

import com.formAuthentication.security.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserDetailsService userDetailsService;
    private CustomSuccessHandler successHandler;
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    private CustomAuthenticationFailureHandler failureHandler;
    private CustomAccessDeniedHandler accessDeniedHandler;
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService,
                                 CustomSuccessHandler successHandler,
                                 CustomAuthenticationFailureHandler failureHandler,
                                 CustomAuthenticationSuccessHandler authenticationSuccessHandler,
                                 CustomLogoutSuccessHandler logoutSuccessHandler,
                                 CustomAccessDeniedHandler accessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .csrf().disable() // We don't need CSRF for this exaxmple
           .authorizeRequests()
              .antMatchers("/","/login","h2-console/**","/api/all-users").permitAll()
              .antMatchers("/profile/**").authenticated()
              .antMatchers("/admin/**").hasRole("ADMIN")
              .antMatchers("/management/**").hasAnyRole("ADMIN","MANAGER")
              .antMatchers("/api/test1").hasAuthority("ACCESS_TEST1")
              .antMatchers("/api/test2").hasAuthority("ACCESS_TEST2")
           .and().csrf().ignoringAntMatchers("/h2-console/**")//don't apply CSRF protection to /h2-console
           .and().headers().frameOptions().sameOrigin()//allow use of frame to same origin urls
           .and()
              .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(failureHandler)
           .and()
              .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler)
           .and()
               .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }




   /** @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities("ACCESS_TEST1","ACCESS_TEST2","ROLE_ADMIN")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("manager")
                .password(passwordEncoder().encode("manager123"))
                .authorities("ACCESS_TEST1","ROLE_MANAGER");
    } **/
}
