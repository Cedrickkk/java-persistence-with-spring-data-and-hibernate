package collections.setofembeddablesorderby;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
    @OrderBy("filename DESC, width DESC")
    private Set<Image> images = new LinkedHashSet<>();

}
