import config.SpringDataConfiguration;
import models.Dimensions;
import models.Item;
import models.Weight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.ItemRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJpaTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void storeLoadEntities() {
        Item item = Item.builder()
                .name("Item 1")
                .dimensions(Dimensions.centimeters(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE))
                .weight(Weight.kilograms(BigDecimal.ONE))
                .build();
        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("Item 1", items.get(0).getName())
        );

    }
}
