import config.SpringDataConfiguration;
import models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringJUnitConfig(classes = {SpringDataConfiguration.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SpringDataJpaMappingTest {
    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        userRepository.saveAll(generateUsers());
    }

    private List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName(UUID.randomUUID().toString());
            users.add(user);
        }
        return users;
    }

    @AfterAll
    void afterAll() {
        userRepository.deleteAll();
    }
}
