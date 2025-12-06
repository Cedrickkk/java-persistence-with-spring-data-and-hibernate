package collections.listofstrings;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @CollectionTable(name = "image")
    @OrderColumn // Enables persistent order, will default to "images_order" column name
    @Column(name = "filename")
    private List<String> images = new ArrayList<>();
}
