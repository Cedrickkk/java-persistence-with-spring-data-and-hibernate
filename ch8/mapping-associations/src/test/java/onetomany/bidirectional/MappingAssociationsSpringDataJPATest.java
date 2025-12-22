package onetomany.bidirectional;

import config.bidirectional.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.bidirectional.BidRepository;
import repositories.bidirectional.ItemRepository;

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

        itemRepository.save(item);

        item.addBid(bid1);
        item.addBid(bid2);

        bidRepository.save(bid1);
        bidRepository.save(bid2);

        List<Item> items = itemRepository.findAll();

        Set<Bid> bids = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size())
        );

    }

}
