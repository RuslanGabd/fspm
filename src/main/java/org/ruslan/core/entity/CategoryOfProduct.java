package org.ruslan.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "category_of_product")
public class CategoryOfProduct extends BaseEntity<Long> {

    private String name;

}
