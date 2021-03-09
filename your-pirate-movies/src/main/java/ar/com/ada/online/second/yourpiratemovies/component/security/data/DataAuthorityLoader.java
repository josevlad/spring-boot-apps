package ar.com.ada.online.second.yourpiratemovies.component.security.data;

import ar.com.ada.online.second.yourpiratemovies.model.entity.security.Authority;
import ar.com.ada.online.second.yourpiratemovies.model.entity.security.AuthorityName;
import ar.com.ada.online.second.yourpiratemovies.model.repository.security.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component @Order(1)
public class DataAuthorityLoader implements ApplicationRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataAuthorityLoader.class);

    @Autowired
    private AuthorityRepository authorityRepository;

    @Value("${spring.application.env}")
    private String appEnv;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("DataRoleLoader.run");

        if (appEnv.equals("dev") || appEnv.equals("qa")) {
            LOGGER.info("Loading initial data Authority");

            Authority admin = new Authority()
                    .setId(1L)
                    .setName(AuthorityName.ROLE_ADMIN);

            Authority manager = new Authority()
                    .setId(2L)
                    .setName(AuthorityName.ROLE_MANAGER);

            Authority user = new Authority()
                    .setId(3L)
                    .setName(AuthorityName.ROLE_USER);

            List<Authority> authorityList = Arrays.asList(admin, manager, user);
            authorityRepository.saveAll(authorityList);
        }
    }
}
