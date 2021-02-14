package co.ratethem.entity;

import co.ratethem.entity.enums.EntityStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
