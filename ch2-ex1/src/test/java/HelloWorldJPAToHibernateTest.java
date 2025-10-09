import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;

public class HelloWorldJPAToHibernateTest {
    private static SessionFactory getSessionFactory(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }
}
