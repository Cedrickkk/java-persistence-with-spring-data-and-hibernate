import config.SpringDataConfiguration;
import models.Address;
import models.Item;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.ItemRepository;
import repositories.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingValuesSpringDataJpaTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeEntities() {
        User user = new User();
        user.setUsername("hula@nm0");
        user.setHomeAddress(new Address("F. Manalo Street", "1600", "Pasig City"));
        userRepository.save(user);

        Item item = new Item();
        item.setName("Mechanical Keyboard");
        item.setMetricWeight(2);
        item.setDescription("Some description");
        itemRepository.save(item);

        List<User> users = userRepository.findAll();

        List<Item> items = itemRepository.findByMetricWeight(2.0);

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("hula@nm0", users.get(0).getUsername()),
                () -> assertEquals("F. Manalo Street", users.get(0)
                        .getHomeAddress()
                        .getStreet()),
                () -> assertEquals("1600", users.get(0)
                        .getHomeAddress()
                        .getZipcode())
        );
    }
}
