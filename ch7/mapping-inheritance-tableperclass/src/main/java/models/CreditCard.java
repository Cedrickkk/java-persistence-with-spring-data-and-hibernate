package models;

import jakarta.persistence.Entity;
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
public class CreditCard extends BillingDetails {
    @NotNull
    private String cardNumber;

    @NotNull
    private String expirationMonth;

    @NotNull
    private String expirationYear;
}
