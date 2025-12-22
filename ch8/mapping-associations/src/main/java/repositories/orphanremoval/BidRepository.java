package repositories.orphanremoval;

import onetomany.orphanremoval.Bid;
import onetomany.orphanremoval.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {

    Set<Bid> findByItem(Item item);
}
