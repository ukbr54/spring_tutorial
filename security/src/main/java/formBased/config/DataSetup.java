package formBased.config;

import formBased.persistence.model.Role;
import formBased.persistence.model.User;
import formBased.persistence.model.UserRole;
import formBased.persistence.repositories.UserRepository;
import formBased.persistence.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSetup {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner init(){
        return (s) ->{

            User user = new User("user",passwordEncoder.encode("user"));
            final User savedUser = createIfUserNotFound(user);
            if(savedUser != null){
                final UserRole.Id id = new UserRole.Id(savedUser.getId(), Role.USER);
                userRoleRepository.save(new UserRole(id,Role.USER));

            }

            User admin = new User("admin",passwordEncoder.encode("admin"));
            final User savedAdmin = createIfUserNotFound(admin);
            if(savedAdmin != null){
                final UserRole.Id id = new UserRole.Id(savedAdmin.getId(), Role.ADMIN);
                userRoleRepository.save(new UserRole(id,Role.USER));

            }

        };
    }

    private User createIfUserNotFound(User user){
        return userRepository.findByUsername(user.getUsername())
                .orElseGet(() -> userRepository.save(user));
    }
}
