package backhoaactive.example.expense.config;

import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.User.repository.UserRepository;
import backhoaactive.example.expense.enums.Roles;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";
    @NonFinal
    static final String MANAGER_USER_NAME = "manager";

    @NonFinal
    static final String MANAGER_PASSWORD = "manager";
    @NonFinal
    static final String FINANCE_USER_NAME = "finance";

    @NonFinal
    static final String FINANCE_PASSWORD = "finance";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "org.postgresql.Driver"
    )
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {

                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(Roles.ADMIN)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            else{
                log.warn("admin user already exists");
            }
            if (userRepository.findByUsername(MANAGER_USER_NAME).isEmpty()) {

                User user = User.builder()
                        .username(MANAGER_USER_NAME)
                        .password(passwordEncoder.encode(MANAGER_PASSWORD))
                        .role(Roles.MANAGER)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            else{
                log.warn("admin user already exists");
            }
            if (userRepository.findByUsername(FINANCE_USER_NAME).isEmpty()) {

                User user = User.builder()
                        .username(FINANCE_USER_NAME)
                        .password(passwordEncoder.encode(FINANCE_PASSWORD))
                        .role(Roles.FINANCE_MANAGER)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            else{
                log.warn("admin user already exists");
            }
            log.info("Application initialization completed .....");
        };
    }
}
