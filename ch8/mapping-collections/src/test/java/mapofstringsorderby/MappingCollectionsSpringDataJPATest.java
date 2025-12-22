package mapofstringsorderby;

import collections.mapofstringsorderby.Item;
import config.mapofstringsorderby.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.mapofstringsorderby.ItemRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingCollectionsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {
        Item item = Item.builder()
                .name("Foo")
                .images(new LinkedHashMap<>(Map.of(
                        "Background", "background.png",
                        "Foreground", "foreground.png",
                        "Landscape", "landscape.png",
                        "Portrait", "portrait.png"
                )))
                .build();
        itemRepository.save(item);

        Item _item = itemRepository.findItemWithImages(item.getId());
        List<Item> items = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());

        assertAll(
                () -> assertEquals(4, _item.getImages().size()),
                () -> assertEquals(1, items.size()),
                () -> assertEquals(4, images.size()),
                () -> assertEquals("Portrait", new ArrayList<>(_item.getImages().keySet()).get(0)),
                () -> assertEquals("Background", new ArrayList<>(_item.getImages().keySet()).get(_item.getImages().size() - 1))
        );
    }


}
