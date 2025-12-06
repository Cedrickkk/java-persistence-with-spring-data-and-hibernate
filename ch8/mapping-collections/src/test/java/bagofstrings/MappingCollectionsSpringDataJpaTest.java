package bagofstrings;

import collections.bagofstrings.Item;
import config.bagofstrings.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.bagofstrings.ItemRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingCollectionsSpringDataJpaTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void storeLoadEntities() {
        Item item = Item.builder()
                .name("Foo")
                .images(Set.of("background.png", "foreground.png", "landscape.png", "portrait.png"))
                .build();

        itemRepository.save(item);

        Item _item = itemRepository.findItemWithImages(item.getId());
        List<Item> items = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());

        assertAll(
                () -> assertEquals(4, _item.getImages().size()),
                () -> assertEquals(1, items.size()),
                () -> assertEquals(4, images.size())
        );
    }
}
