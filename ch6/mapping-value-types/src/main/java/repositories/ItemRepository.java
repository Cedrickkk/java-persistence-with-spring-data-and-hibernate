package repositories;

import models.Item;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ItemRepository extends ListCrudRepository<Item, Long> {
    List<Item> findByMetricWeight(double weight);
}
