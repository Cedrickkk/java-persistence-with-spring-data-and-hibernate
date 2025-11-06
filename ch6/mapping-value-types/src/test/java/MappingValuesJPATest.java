import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappingValuesJPATest {
    @Test
    void storeLoadEntities() {
        try (EntityManagerFactory factory =
                     Persistence.createEntityManagerFactory("ch6.mapping_value_types");
             EntityManager entityManager = factory.createEntityManager()) {

            entityManager.getTransaction().begin();

            Item item = new Item();
            item.setName("Some Item");
            item.setMetricWeight(2);
            item.setInitialPrice(BigDecimal.valueOf(1.00));
            item.setDescription("descriptiondescription");

            entityManager.persist(item);
            entityManager.getTransaction().commit();

            System.out.println(item);

//            entityManager.clear();

            entityManager.getTransaction().begin();
            List<Item> items =
                    entityManager.createQuery("SELECT i FROM Item i WHERE i.metricWeight = :weight", Item.class)
                            .setParameter("weight", 2.0)
                            .getResultList();

            entityManager.getTransaction().commit();

            System.out.println(items.get(0));

            assertAll(
                    () -> assertEquals("AUCTION: Some Item", items.get(0).getName()),
                    () -> assertEquals("descriptiondescription", items.get(0).getDescription()),
                    () -> assertEquals(2.0, items.get(0).getMetricWeight())
            );
        }
    }
}
