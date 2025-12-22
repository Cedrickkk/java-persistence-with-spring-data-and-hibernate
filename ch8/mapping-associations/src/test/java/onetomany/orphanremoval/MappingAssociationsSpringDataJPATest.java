package onetomany.orphanremoval;

import config.orphanremoval.SpringDataConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import repositories.orphanremoval.BidRepository;
import repositories.orphanremoval.ItemRepository;
import repositories.orphanremoval.UserRepository;

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
    @Autowired
    private UserRepository userRepository;

    @Test
    void storeLoadEntities() {
        User user = User.builder().name("John Smith").build();
        userRepository.save(user);

        Item tumbler = Item.builder().name("Aqua Flask Tumbler").build();

        Bid tumblerBid1 = Bid.builder()
                .item(tumbler)
                .amount(BigDecimal.valueOf(50))
                .bidder(user)
                .build();

        tumbler.addBid(tumblerBid1);

        Bid tumblerBid2 = Bid.builder()
                .item(tumbler)
                .amount(BigDecimal.valueOf(100))
                .bidder(user)
                .build();

        tumbler.addBid(tumblerBid2);

        itemRepository.save(tumbler);

        List<Item> items = itemRepository.findAll();
        Set<Bid> bids = bidRepository.findByItem(tumbler);
        User john = userRepository.findUserWithBids(user.getId());

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size()),
                () -> assertEquals(2, john.getBids().size())
        );

        Item tumblerWithBids = itemRepository.findItemWithBids(tumbler.getId());
        Bid firstTumblerBid = tumblerWithBids.getBids().iterator().next();
        tumblerWithBids.removeBid(firstTumblerBid);
        itemRepository.save(tumblerWithBids);

        List<Item> itemList = itemRepository.findAll();
        List<Bid> bidList = bidRepository.findAll();

        assertAll(
                () -> assertEquals(1, itemList.size()),
                () -> assertEquals(1, bidList.size()),
                () -> assertEquals(2, john.getBids().size()),
                () -> assertEquals(0, john.getBids().size()) // Fail
        );
    }

}
