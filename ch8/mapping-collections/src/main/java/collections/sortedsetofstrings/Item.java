package collections.sortedsetofstrings;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import java.util.SortedSet;
import java.util.TreeSet;

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
    @SortNatural
    @Column(name = "filename")
    private SortedSet<String> images = new TreeSet<>();
}