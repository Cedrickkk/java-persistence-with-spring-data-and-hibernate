package collections.bagofstrings;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CollectionIdJavaClass;

import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "BagItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "image_id_seq_gen", sequenceName = "image_id_seq", allocationSize = 1)
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq_gen")
    @SequenceGenerator(name = "item_id_seq_gen", sequenceName = "item_id_seq")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "filename")
    @CollectionId(column = @Column(name = "image_id"), generator = "image_id_seq_gen")
    @CollectionIdJavaClass(idType = Long.class)
    private Collection<String> images = new ArrayList<>();

    public void addImage(String image) {
        this.images.add(image);
    }

}
