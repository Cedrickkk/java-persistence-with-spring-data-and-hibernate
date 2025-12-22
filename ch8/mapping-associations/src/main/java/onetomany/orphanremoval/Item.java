package onetomany.orphanremoval;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Bid> bids = new HashSet<>();

    public void addBid(Bid bid) {
        if (bid != null) {
            bids.add(bid);
        }
    }

    public void removeBid(Bid bid) {
        bids.remove(bid);
    }
}
