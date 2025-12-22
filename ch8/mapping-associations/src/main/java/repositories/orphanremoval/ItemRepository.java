package repositories.orphanremoval;

import onetomany.orphanremoval.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i INNER JOIN FETCH i.bids WHERE i.id = :id")
    Item findItemWithBids(@Param("id") Long id);

}
