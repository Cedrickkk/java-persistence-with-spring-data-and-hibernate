package collections.mapofstringsorderby;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashMap;
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

    @ElementCollection
    @CollectionTable(name = "image")
    @MapKeyColumn(name = "filename")
    @Column(name = "image_name")
    @OrderBy("filename DESC")
    private Map<String, String> images = new LinkedHashMap<>();
}