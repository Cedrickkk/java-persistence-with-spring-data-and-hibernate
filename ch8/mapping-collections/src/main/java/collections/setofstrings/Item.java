package collections.setofstrings;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq_gen")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "item_id"))
    @SequenceGenerator(name = "item_id_seq_gen", sequenceName = "item_id_seq")
    @Column(name = "filename")
    private Set<String> images = new HashSet<>();

    public void addImage(String image) {
        images.add(image);
    }
}
