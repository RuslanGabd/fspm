package org.ruslan.core.mapper;

import org.ruslan.core.dao.CategoryOfShopRepository;
import org.ruslan.core.dto.ShopDto;
import org.ruslan.core.entity.CategoryOfShop;
import org.ruslan.core.entity.Shop;
import org.springframework.stereotype.Service;

@Service
public class ShopMapper {

    private final CategoryOfShopRepository categoryOfShopRepository;

    public ShopMapper(CategoryOfShopRepository categoryOfShopRepository) {
        this.categoryOfShopRepository = categoryOfShopRepository;
    }

    public ShopDto mapToShopDto(Shop shop) {
        return new ShopDto(shop.getId(),
                shop.getName(),
                shop.getAddress(),
                shop.getCategoryOfShop().
                        stream().
                        map(CategoryOfShop::getName).
                        toList());
    }

    public Shop mapToShop(ShopDto dto) {
        return new Shop(dto.getName(),
                dto.getAddress(),
                dto.getCategories().
                        stream().
                        map(name ->
                                categoryOfShopRepository.
                                        findByName(name).
                                        orElse(null)).
                        toList());
    }
}
