package models;

import jakarta.persistence.DiscriminatorValue;
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
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails {

    @NotNull
    private String account;

    @NotNull
    private String bankName;

    @NotNull
    private String swift;

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + super.getId() +
                ", owner='" + super.getOwner() + '\'' +
                ", account='" + account + '\'' +
                ", bankName='" + bankName + '\'' +
                ", swift='" + swift + '\'' +
                '}';
    }
}
