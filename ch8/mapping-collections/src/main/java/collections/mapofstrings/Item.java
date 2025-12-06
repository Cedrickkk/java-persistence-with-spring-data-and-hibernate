package collections.mapofstrings;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

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

    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "image")
    @MapKeyColumn(name = "filename")
    @Column(name = "image_name")
    private Map<String, String> images = new HashMap<>();
}