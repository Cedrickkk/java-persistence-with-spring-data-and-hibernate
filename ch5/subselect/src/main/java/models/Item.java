package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_sequence_generator")
    @SequenceGenerator(
            name = "item_id_sequence_generator",
            sequenceName = "id_sequence"
    )
    private Long id;

    @Version
    private Long version;

    @NotNull
    @Size(min = 2, max = 255, message = "Name is required, maximum 255 characters.")
    private String name;

    @Future
    private Date auctionEnd;

    @Transient
    private Set<Bid> bids = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }
}
