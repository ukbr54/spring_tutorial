package formBased.config;

import formBased.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomLogoutSuccessHandler logoutSuccessHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomLoginSuccessHandler loginSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final UserDetailService userDetailService;

    @Autowired
    public SecurityConfig(CustomLogoutSuccessHandler logoutSuccessHandler,
                          CustomAccessDeniedHandler accessDeniedHandler,
                          CustomLoginSuccessHandler loginSuccessHandler,
                          CustomAuthenticationFailureHandler authenticationFailureHandler,
                          UserDetailService userDetailService) {
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.loginSuccessHandler = loginSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .authorizeRequests()
                .antMatchers(permitUrls).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
           .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")//don't apply CSRF protection to /h2-console
           .and()
                .headers()
                .frameOptions()
                .sameOrigin()//allow use of frame to same origin urls
           .and()
           .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .successHandler(loginSuccessHandler)
                //.defaultSuccessUrl("/home")
                //.failureUrl("/login?error=true")
                .failureHandler(authenticationFailureHandler)
           .and()
           .logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .deleteCookies("JSESSIONID")
               .invalidateHttpSession(true)
               .logoutSuccessHandler(logoutSuccessHandler)
               //.logoutSuccessUrl("/login?logout=true")
           .and()
           .exceptionHandling()
                //.accessDeniedPage("/access-denied");
                 .accessDeniedHandler(accessDeniedHandler);
    }

    private String[] permitUrls = {
            "/", "/login/**","/h2-console/**"
    };

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder.encode("user")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
//    }
}
