package onetomany.cascadepersist;

import config.cascadepersist.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.cascadepersist.BidRepository;
import repositories.cascadepersist.ItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingAssociationsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;


    @Test
    void storeLoadEntities() {
        Item item = Item.builder().name("Foo").build();

        Bid bid1 = Bid.builder().amount(BigDecimal.valueOf(100)).item(item).build();
        Bid bid2 = Bid.builder().amount(BigDecimal.valueOf(200)).item(item).build();

        item.addBid(bid1);
        item.addBid(bid2);

        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();
        Set<Bid> bids = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size())
        );

        Item retrievedItem = itemRepository.findById(item.getId()).get();

        for (Bid _bid : bidRepository.findByItem(retrievedItem)) {
            bidRepository.delete(_bid);
        }

        itemRepository.delete(retrievedItem);

        List<Item> _items = itemRepository.findAll();
        Set<Bid> _bids = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(0, _items.size()),
                () -> assertEquals(0, _bids.size())
        );
    }

}
