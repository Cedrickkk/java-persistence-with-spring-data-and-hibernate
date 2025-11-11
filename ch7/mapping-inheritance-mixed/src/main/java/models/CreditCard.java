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
@DiscriminatorValue("CC")
@SecondaryTable(name = "creditcard", pkJoinColumns = @PrimaryKeyJoinColumn(name = "credit_card_id"))
public class CreditCard extends BillingDetails {
    @NotNull
    @Column(table = "creditcard", nullable = false)
    private String cardNumber;

    @NotNull
    @Column(table = "creditcard", nullable = false)
    private String expirationMonth;

    @NotNull
    @Column(table = "creditcard", nullable = false)
    private String expirationYear;
}
