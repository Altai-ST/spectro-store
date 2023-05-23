package engine.spectro.entity;

import engine.spectro.enums.GeneralProductEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "mother_board")
public class MotherBoard extends ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String model;

    @Column
    String image;

    @Column
    int amount;

    @Column
    String brand;

    @Column
    BigDecimal price;

    @Column(name = "form_factor")
    String formFactor;

    @Column
    String processor;

    @Column
    String socket;

    @Column
    String chipset;

    @Column
    @Enumerated(EnumType.STRING)
    GeneralProductEnum status;
}
