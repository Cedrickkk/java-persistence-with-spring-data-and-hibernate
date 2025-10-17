package models;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Immutable
@Subselect(value = "SELECT i.id AS itemId, i.name AS name, " +
        "COUNT(b.id) AS numberOfBids " +
        "FROM Item i LEFT OUTER JOIN Bid b ON i.id = b.item_id " +
        "GROUP BY i.id, i.name")
@Synchronize({"Item", "Bid"})
public class ItemBidSummary {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_bid_summary_id_sequence_generator"
    )
    @SequenceGenerator(
            name = "item_bid_summary_id_sequence_generator",
            sequenceName = "item_bid_summary_id_sequence"
    )
    private Long itemId;

    private String name;

    private Long numberOfBids;

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Long numberOfBids) {
        this.numberOfBids = numberOfBids;
    }
}
