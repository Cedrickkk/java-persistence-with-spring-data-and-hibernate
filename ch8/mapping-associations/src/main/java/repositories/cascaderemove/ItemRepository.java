package repositories.cascaderemove;

import onetomany.cascaderemove.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
