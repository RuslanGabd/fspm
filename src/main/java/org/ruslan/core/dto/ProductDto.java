package org.ruslan.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDto {
    Long id;
    String name;
    String description;
    List<String> categories;


}
