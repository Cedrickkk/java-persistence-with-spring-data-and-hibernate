package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DiscriminatorFormula;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("CASE WHEN cardNumber IS NOT NULL THEN 'CC' ELSE 'BA' END")
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_details_id_seq_gen")
    @SequenceGenerator(
            name = "billing_details_id_seq_gen",
            sequenceName = "billing_id_seq"
    )
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String owner;

    @Override
    public String toString() {
        return "BillingDetails{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                '}';
    }
}
