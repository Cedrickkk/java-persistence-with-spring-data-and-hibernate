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
public class BankAccount extends BillingDetails {

    @NotNull
    private String account;

    @NotNull
    private String bankName;

    @NotNull
    private String swift;

    @Override
    public void pay(Long amount) {
        System.out.println("Paying from a bank account the amount of " + amount);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "account='" + account + '\'' +
                ", bankName='" + bankName + '\'' +
                ", swift='" + swift + '\'' +
                '}';
    }
}
