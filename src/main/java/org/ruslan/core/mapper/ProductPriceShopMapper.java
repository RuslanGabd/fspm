package org.ruslan.core.mapper;

import org.ruslan.core.dao.ProductRepository;
import org.ruslan.core.dao.ShopRepository;
import org.ruslan.core.dto.ProductPriceShopDto;
import org.ruslan.core.entity.ProductPriceShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPriceShopMapper {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public ProductPriceShopMapper(ProductRepository productRepository, ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    public ProductPriceShopDto mapToProductPriceShopDto(ProductPriceShop pps) {
        return new ProductPriceShopDto(pps.getId(),
                pps.getProduct().getName(),
                pps.getShop().getName(),
                pps.getValuePrice(),
                pps.getDatePrice());
    }

    public ProductPriceShop mapToProductPriceShop(ProductPriceShopDto dto) {
        return new ProductPriceShop(productRepository.findByName(dto.getProductName()).get(),
                shopRepository.findByName(dto.getShopName()).get(),
                dto.getValuePrice(),
                dto.getDatePrice());
    }
}
