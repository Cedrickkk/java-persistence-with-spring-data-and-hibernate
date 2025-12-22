package repositories.cascadepersist;

import onetomany.cascadepersist.Bid;
import onetomany.cascadepersist.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Set<Bid> findByItem(Item item);
}