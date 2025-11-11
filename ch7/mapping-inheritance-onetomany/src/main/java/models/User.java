package models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence_generator")
    @SequenceGenerator(
            name = "user_id_sequence_generator",
            sequenceName = "user_id_sequence"
    )
    private Long id;

    private String username;

    @ManyToOne
    private BillingDetails defaultBilling;

    @OneToMany(mappedBy = "user")
    private Set<BillingDetails> billingDetails = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", defaultBilling=" + defaultBilling +
                ", billingDetails=" + billingDetails +
                '}';
    }
}
