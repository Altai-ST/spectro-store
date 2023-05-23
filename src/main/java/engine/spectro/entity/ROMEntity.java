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
@Table(name = "rom")
public class ROMEntity extends ProductEntity{
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
    @Enumerated(EnumType.STRING)
    GeneralProductEnum status;

    @Column
    String type;

    @Column(name = "form_factor")
    String formFactor;

    @Column(name = "module_amount")
    short moduleAmount;

    @Column
    String size;

    @Column
    short mhz;

    @Column
    BigDecimal price;
}
