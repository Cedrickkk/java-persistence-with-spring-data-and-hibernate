package repositories.cascaderemove;

import onetomany.cascaderemove.Bid;
import onetomany.cascaderemove.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {

    Set<Bid> findByItem(Item item);

}
