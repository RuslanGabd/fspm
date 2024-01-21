package org.ruslan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "category_of_shop")
@RequiredArgsConstructor
public class CategoryOfShop extends Category {

       @Builder
       public CategoryOfShop(String name){
              super(name);

       }
}
