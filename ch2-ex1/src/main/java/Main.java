import models.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Message.class);
        try (ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
             SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();

            Message hibernate = new Message();
            hibernate.setText("Test native hibernate");

            session.persist(hibernate);

            List<Message> resultList = session.createQuery("SELECT m FROM models.Message m", Message.class).getResultList();

            resultList.forEach(message -> System.out.println("MESSAGE: " + message.getText()));

            session.getTransaction().commit();
        }
    }
}
