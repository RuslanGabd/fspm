package org.ruslan.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ProductPriceShopDto {

    Long id;
    String productName;
    String shopName;
    BigDecimal valuePrice;
    LocalDate datePrice;

}
