package formBased.config;

import formBased.component.CustomAccessDeniedHandler;
import formBased.component.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomLogoutSuccessHandler logoutSuccessHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(CustomLogoutSuccessHandler logoutSuccessHandler,
                          CustomAccessDeniedHandler accessDeniedHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
           .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
           .and()
           .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
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


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("user")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }
}
