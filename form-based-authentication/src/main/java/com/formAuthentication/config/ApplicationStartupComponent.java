package com.formAuthentication.config;

import com.formAuthentication.persistence.model.*;
import com.formAuthentication.persistence.repositories.UserRepository;
import com.formAuthentication.persistence.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Ujjwal Gupta on Dec,2019
 */

@Configuration
public class ApplicationStartupComponent {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner init(){
        return (s) ->{
            saveUser();
        };
    }

    private void saveUser(){
        User user = new User("user",passwordEncoder.encode("user"));
        final User savedUser = createIfUserNotFound(user);
        userRoleRepository.save(new UserRole(new UserRole.Id(savedUser.getId(), Role.USER), Role.USER));


        User admin = new User("admin",passwordEncoder.encode("admin"));
        final User userAdmin = createIfUserNotFound(admin);
        userRoleRepository.save(new UserRole(new UserRole.Id(userAdmin.getId(), Role.ADMIN), Role.ADMIN));


        User manager = new User("manager",passwordEncoder.encode("manager"));
        final User userManager = createIfUserNotFound(manager);
        userRoleRepository.save(new UserRole(new UserRole.Id(userManager.getId(), Role.MANAGER), Role.MANAGER));
    }

    private User createIfUserNotFound(User user){
        return userRepository.findByUsername(user.getUsername()).orElseGet(() -> userRepository.save(user));
    }
}
