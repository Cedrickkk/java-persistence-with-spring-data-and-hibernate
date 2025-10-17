import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.Bid;
import models.Item;
import models.ItemBidSummary;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemBidSummaryTest {
    @Test
    void testItemBidSummary() {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("subselect");
             EntityManager em = factory.createEntityManager()) {
            em.getTransaction().begin();

            Item item = new Item();
            item.setName("Some Item");
            item.setAuctionEnd(Helper.tomorrow());

            em.persist(item);

            Bid bid1 = new Bid(new BigDecimal("1000.0"), item);
            Bid bid2 = new Bid(new BigDecimal("1100.0"), item);


            em.persist(bid1);
            em.persist(bid2);

            em.getTransaction().commit();

            em.getTransaction().begin();

            TypedQuery<ItemBidSummary> query = em.createQuery("SELECT ibs FROM ItemBidSummary ibs " +
                    "WHERE ibs.itemId = :id", ItemBidSummary.class);

            ItemBidSummary itemBidSummary = query.setParameter("id", 1).getSingleResult();

            assertAll(
                    () -> assertEquals(1, itemBidSummary.getItemId()),
                    () -> assertEquals("Some Item", itemBidSummary.getName())
            );

        }
    }
}
