package engine.spectro.entity;

import engine.spectro.enums.GeneralProductEnum;
import lombok.Data;

@Data
public abstract class ProductEntity {
    Long id;
    String model;
    int amount;
    String image;
    GeneralProductEnum status;
}
