import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.*;
import models.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MetaModelTest {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void beforeAll() {
        entityManagerFactory = Persistence.createEntityManagerFactory("metamodel");
    }

    @Test
    public void accessDynamicMetamodel() {
        Metamodel metamodel = entityManagerFactory.getMetamodel();
        Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();
        ManagedType<?> itemType = managedTypes.iterator().next();
        assertAll(
                () -> assertEquals(1, managedTypes.size()),
                () -> assertEquals(Type.PersistenceType.ENTITY, itemType.getPersistenceType())
        );

        SingularAttribute<?, ?> idAttribute = itemType.getSingularAttribute("id");
        assertFalse(idAttribute.isOptional());
        assertTrue(idAttribute.isId());

        SingularAttribute<?, ?> nameAttribute = itemType.getSingularAttribute("name");
        assertAll(
                () -> assertEquals(String.class, nameAttribute.getJavaType()),
                () -> assertEquals(Attribute.PersistentAttributeType.BASIC, nameAttribute.getPersistentAttributeType())
        );

        SingularAttribute<?, ?> auctionEndAttribute = itemType.getSingularAttribute("auctionEnd");
        assertAll(
                () -> assertEquals(Timestamp.class, auctionEndAttribute.getJavaType()),
                () -> assertFalse(auctionEndAttribute.isCollection()),
                () -> assertFalse(auctionEndAttribute.isAssociation())
        );
    }

    @Test
    public void accessStaticMetamodel() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        Root<Item> from = query.from(Item.class);
        query.select(from);
        List<Item> items = entityManager.createQuery(query).getResultList();
        assertEquals(0, items.size());

        Path<String> namePath = from.get("name");
        query.where(criteriaBuilder.like(namePath, criteriaBuilder.parameter(String.class, "pattern")));

//        query.where(
//                criteriaBuilder.like(
//                        from.get(Item_.name),
//                        criteriaBuilder.parameter(String.class, "pattern")
//                )
//        );

        List<Item> _items = entityManager.createQuery(query).
                setParameter("pattern", "%Item 1%").
                getResultList();
        assertAll(() -> assertEquals(0, items.size()),
                () -> assertEquals("Item 1", items.iterator()
                        .next()
                        .getName()));
    }
}
