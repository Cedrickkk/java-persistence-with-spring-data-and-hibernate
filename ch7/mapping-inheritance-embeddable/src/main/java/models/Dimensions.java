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
@AttributeOverride(name = "name", column = @Column(name = "dimensions_name"))
@AttributeOverride(name = "symbol", column = @Column(name = "dimensions_symbol"))
@SuperBuilder
public class Dimensions extends Measurement {

    @NotNull
    private BigDecimal depth;

    @NotNull
    private BigDecimal width;

    @NotNull
    private BigDecimal height;

    public static Dimensions centimeters(BigDecimal width, BigDecimal height, BigDecimal depth) {
        return Dimensions.builder()
                .name("centimeters")
                .symbol("cm")
                .width(width)
                .height(height)
                .depth(depth)
                .build();
    }

    public static Dimensions inches(BigDecimal width, BigDecimal height, BigDecimal depth) {
        return Dimensions.builder()
                .name("inches")
                .symbol("\"")
                .width(width)
                .height(height)
                .depth(depth)
                .build();
    }

}
