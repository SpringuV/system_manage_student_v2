package project.com.example.my_project_manage_student_system.SecurityConfiguration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.com.example.my_project_manage_student_system.Entity.Role;
import project.com.example.my_project_manage_student_system.Entity.User;
import project.com.example.my_project_manage_student_system.Repository.RoleRepository;
import project.com.example.my_project_manage_student_system.Repository.UserRepository;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfiguration {
    PasswordEncoder passwordEncoder;
    static final String USER_ADMIN_ID = "admin";
    static final String ADMIN_PASSWORD = "1234";

    @Bean //khi có class name của mysql thì mới init bean lên, không thì sẽ dùng driverClass của H2 để test
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        return args -> {
            if(userRepository.findByUserId(USER_ADMIN_ID).isEmpty()){
                // create role
                Role adminRole = roleRepository.save(
                    Role.builder().roleName("ADMIN").description("Admin role").build()
                );

                roleRepository.save(Role.builder().roleName("USER").description("User role").build());

                User user = User.builder().userId(USER_ADMIN_ID).role(adminRole).password(passwordEncoder.encode(ADMIN_PASSWORD)).build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
