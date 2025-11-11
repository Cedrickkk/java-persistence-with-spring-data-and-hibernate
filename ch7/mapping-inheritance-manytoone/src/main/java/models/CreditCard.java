package models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name = "credit_card_id")
public class CreditCard extends BillingDetails {

    @NotNull
    private String cardNumber;

    @NotNull
    private String expirationMonth;

    @NotNull
    private String expirationYear;

    @Override
    public void pay(Long amount) {
        System.out.println("Paying from a credit card with the amount of: " + amount);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationMonth='" + expirationMonth + '\'' +
                ", expirationYear='" + expirationYear + '\'' +
                '}';
    }
}
