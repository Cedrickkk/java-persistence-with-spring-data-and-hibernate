package models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@AttributeOverride(name = "name", column = @Column(name = "weight_name"))
@AttributeOverride(name = "symbol", column = @Column(name = "weight_symbol"))
public class Weight extends Measurement {
    @NotNull
    @Column(name = "weight")
    private BigDecimal value;

    public static Weight kilograms(BigDecimal weight) {
        return Weight.builder()
                .name("kilograms")
                .symbol("kg")
                .value(weight)
                .build();
    }

    public static Weight pounds(BigDecimal weight) {
        return Weight.builder()
                .name("pounds")
                .symbol("lbs")
                .value(weight)
                .build();
    }
}
