package org.ruslan.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShopDto {
    Long id;
    String name;
    String address;
    List<String> categories;

}
