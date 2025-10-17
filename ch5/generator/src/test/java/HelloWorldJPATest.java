import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldJPATest {
    @Test
    public void storeLoadItem() {
        try (EntityManagerFactory emf =
                     Persistence.createEntityManagerFactory("ch5.generator");
             EntityManager manager = emf.createEntityManager()) {

            manager.getTransaction().begin();

            Item item = new Item();
            item.setName("Some Item");
            item.setAuctionEnd(Helper.tomorrow());

            manager.persist(item);

            manager.getTransaction().commit();

            manager.getTransaction().begin();

//            List<Item> items = manager.createQuery("SELECT i FROM Item i", Item.class).getResultList();

            List<Item> items = manager.createQuery("SELECT i FROM AuctionItem i", Item.class).getResultList();

            manager.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, items.size()),
                    () -> assertEquals("Some Item", items.get(0).getName())
            );
        }
    }
}
