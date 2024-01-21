package org.ruslan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "category_of_product")
public class CategoryOfProduct extends Category {

    @Builder
    public CategoryOfProduct(String name){
        super(name);

    }


}
