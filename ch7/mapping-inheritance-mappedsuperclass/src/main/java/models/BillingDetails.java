package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_details_id_seq_gen")
    @SequenceGenerator(
            name = "billing_details_id_seq_gen",
            sequenceName = "billing_id_seq"
    )
    private Long id;

    private String owner;
}
