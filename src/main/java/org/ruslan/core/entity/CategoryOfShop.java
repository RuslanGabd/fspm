package org.ruslan.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "category_of_shop")
@RequiredArgsConstructor
public class CategoryOfShop extends BaseEntity<Long> {

    private String name;
}
