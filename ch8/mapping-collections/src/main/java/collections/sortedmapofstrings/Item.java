package collections.sortedmapofstrings;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SortComparator;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

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
    @SortComparator(ReverseStringComparator.class)
    private SortedMap<String, String> images = new TreeMap<>();

    public static class ReverseStringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.compareTo(o1);
        }
    }

}