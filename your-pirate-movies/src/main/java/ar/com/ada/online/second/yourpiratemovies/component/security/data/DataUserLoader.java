package ar.com.ada.online.second.yourpiratemovies.component.security.data;

import ar.com.ada.online.second.yourpiratemovies.model.entity.security.Authority;
import ar.com.ada.online.second.yourpiratemovies.model.entity.security.User;
import ar.com.ada.online.second.yourpiratemovies.model.repository.security.AuthorityRepository;
import ar.com.ada.online.second.yourpiratemovies.model.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Component @Order(2)
public class DataUserLoader implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataUserLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LOGGER.info("DataUserLoader.run");

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading initial data Users");

            Authority adminAuthority = authorityRepository.findById(1L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            Authority managerAuthority = authorityRepository.findById(2L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            Authority userAuthority = authorityRepository.findById(3L)
                    .orElseThrow(() -> new SQLException("Authority Not Found"));

            User admin = new User()
                    .setId(1L).setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setFirstName("admin").setLastName("admin")
                    .setEmail("admin@admin.com").setEnabled(true)
                    .addAuthority(adminAuthority)
                    .addAuthority(managerAuthority)
                    .addAuthority(userAuthority);

            User manager = new User()
                    .setId(2L).setUsername("manager")
                    .setPassword(passwordEncoder.encode("manager"))
                    .setFirstName("manager").setLastName("manager")
                    .setEmail("manager@manager.com").setEnabled(true)
                    .addAuthority(managerAuthority)
                    .addAuthority(userAuthority);

            User user = new User()
                    .setId(3L).setUsername("user")
                    .setPassword(passwordEncoder.encode("user"))
                    .setFirstName("user").setLastName("user")
                    .setEmail("user@user.com").setEnabled(true)
                    .addAuthority(userAuthority);

            List<User> userList = Arrays.asList(admin, manager, user);
            userRepository.saveAll(userList);

        }
    }
}
