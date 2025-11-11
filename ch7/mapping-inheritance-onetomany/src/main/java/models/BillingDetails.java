package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_details_id_seq_gen")
    @SequenceGenerator(
            name = "billing_details_id_seq_gen",
            sequenceName = "billing_id_seq"
    )
    private Long id;

    @NotNull
    private String owner;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "BillingDetails{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                '}';
    }

    public abstract void pay(Long amount);
}
