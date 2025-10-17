import config.SpringDataConfiguration;
import models.Bid;
import models.Item;
import models.ItemBidSummary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.BidRepository;
import repositories.ItemBidSummaryRepository;
import repositories.ItemRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class ItemBidSummarySpringDataJpaTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ItemBidSummaryRepository itemBidSummaryRepository;

    @Test
    void itemBidSummaryTest() {
        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(Helper.tomorrow());

        Bid bid1 = new Bid(new BigDecimal("1000.0"), item);
        Bid bid2 = new Bid(new BigDecimal("2000.0"), item);

        itemRepository.save(item);
        bidRepository.save(bid1);
        bidRepository.save(bid2);

        Optional<ItemBidSummary> itemBidSummary = itemBidSummaryRepository.findById(1L);

        assertAll(
                () -> assertEquals(1, itemBidSummary.orElseThrow().getItemId()),
                () -> assertEquals("Some Item", itemBidSummary.orElseThrow().getName()),
                () -> assertEquals(2, itemBidSummary.orElseThrow().getNumberOfBids())
        );
    }
}
