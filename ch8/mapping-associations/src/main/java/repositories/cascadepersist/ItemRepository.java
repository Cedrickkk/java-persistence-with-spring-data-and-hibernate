package repositories.cascadepersist;

import onetomany.cascadepersist.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
