package listofstrings;

import collections.listofstrings.Item;
import config.listofstrings.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.listofstrings.ItemRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingCollectionsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void storeLoadEntities() {
        Item item1 = Item.builder()
                .name("Item 1")
                .images(List.of("background.png", "foreground.png", "landscape.png", "portrait.png"))
                .build();
        itemRepository.save(item1);

        Item item2 = Item.builder()
                .name("Item 2")
                .images(List.of("portrait.png", "foreground.png"))
                .build();
        itemRepository.save(item2);

        Item _item1 = itemRepository.findItemWithImages(item1.getId());
        Item _item2 = itemRepository.findItemWithImages(item2.getId());
        List<Item> items = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item1.getId());

        assertAll(
                () -> assertEquals(4, _item1.getImages().size()),
                () -> assertEquals(2, _item2.getImages().size()),
                () -> assertEquals(2, items.size()),
                () -> assertEquals(4, images.size())
        );
    }
}
