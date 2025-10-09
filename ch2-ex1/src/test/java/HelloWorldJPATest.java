import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Message;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldJPATest {
    @Test
    public void storeLoadMessage() {
        try(EntityManagerFactory factory =
                    Persistence.createEntityManagerFactory("ch02");
            EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World!");

            manager.persist(message);

            manager.getTransaction().commit();
            // INSERT into MESSAGE (ID, TEXT) VALUES (1, 'Hello World!')

            manager.getTransaction().begin();

            List<Message> messages =
                    manager.createQuery("SELECT m FROM Message m", Message.class)
                            .getResultList();
            // SELECT * FROM MESSAGE

            messages.get(messages.size() - 1)
                    .setText("Hello World from JPA!");

            manager.getTransaction().commit();
            // UPDATE MESSAGE SET text = "Hello World from JPA!" WHERE id = 1

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World from JPA!", messages.get(0).getText())
            );
        }
    }
}
