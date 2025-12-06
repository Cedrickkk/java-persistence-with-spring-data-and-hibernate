package repositories.listofstrings;

import collections.listofstrings.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i INNER JOIN FETCH i.images WHERE i.id = :id")
    Item findItemWithImages(@Param("id") Long id);

    @Query(value = "SELECT i.filename FROM image i WHERE i.item_id = :id", nativeQuery = true)
    Set<String> findImagesNative(@Param("id") Long id);

}
