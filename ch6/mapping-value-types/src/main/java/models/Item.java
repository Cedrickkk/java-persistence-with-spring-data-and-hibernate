package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.*;
import org.hibernate.generator.EventType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_sequence_generator")
    @SequenceGenerator(
            name = "item_id_sequence_generator",
            sequenceName = "item_id_sequence"
    )
    private Long id;

    //    @Basic(optional = false)
    @Column(name = "start_price", nullable = false)
    @ColumnDefault("1.00")
    @Generated(event = EventType.INSERT)
    private BigDecimal initialPrice;

    @Access(AccessType.PROPERTY)
    @Column(name = "item_name")
    private String name;

    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private String description;

    @Formula(
            "CONCAT(SUBSTR(description, 1, 12), '...')"
    )
    private String shortDescription;

    @Formula("(SELECT AVG(b.amount) FROM Bid b WHERE b.item_id = id)")
    private BigDecimal averageBidAmount;

    @Column(name = "imperial_weight")
    @ColumnTransformer(
            read = "imperial_weight / 2.20462",
            write = "? *  2.20462"
    )
    private double metricWeight;

    @Transient
    private Set<Bid> bids = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType = AuctionType.HIGHEST_BID;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public BigDecimal getAverageBidAmount() {
        return averageBidAmount;
    }

    public void setAverageBidAmount(BigDecimal averageBidAmount) {
        this.averageBidAmount = averageBidAmount;
    }

    public double getMetricWeight() {
        return metricWeight;
    }

    public void setMetricWeight(double metricWeight) {
        this.metricWeight = metricWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = !name.startsWith("AUCTION: ") ? "AUCTION: " + name : name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", initialPrice=" + initialPrice +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", averageBidAmount=" + averageBidAmount +
                ", metricWeight=" + metricWeight +
                ", bids=" + bids +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
