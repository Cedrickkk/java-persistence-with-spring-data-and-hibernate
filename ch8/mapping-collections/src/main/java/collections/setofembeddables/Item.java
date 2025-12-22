package collections.setofembeddables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String name;

    @ElementCollection
    @CollectionTable(name = "image")
    @AttributeOverride(name = "filename", column = @Column(name = "fname", nullable = false))
    private Set<Image> images = new HashSet<>();
}