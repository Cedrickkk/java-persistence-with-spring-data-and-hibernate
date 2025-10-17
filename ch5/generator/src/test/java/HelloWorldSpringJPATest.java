import config.SpringDataConfiguration;
import models.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.ItemRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class HelloWorldSpringJPATest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void storeLoadItem() {
        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(Helper.tomorrow());

        itemRepository.save(item);

        Item item1 = new Item();
        item1.setName("test");
        item1.setAuctionEnd(Helper.tomorrow());

        itemRepository.save(item1);

        Iterable<Item> itemIterable = itemRepository.findAll();

        List<Item> items = StreamSupport.stream(itemIterable.spliterator(), false)
                .toList();

        assertAll(
                () -> assertEquals(2, items.size()),
                () -> assertEquals("Some Item", items.get(0).getName())
        );
    }
}
