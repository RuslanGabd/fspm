package org.ruslan.core.mapper;

import org.ruslan.core.dao.CategoryOfProductRepository;
import org.ruslan.core.dto.ProductDto;
import org.ruslan.core.entity.CategoryOfProduct;
import org.ruslan.core.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    private final CategoryOfProductRepository categoryOfProductRepository;

    public ProductMapper(CategoryOfProductRepository categoryOfProductRepository) {
        this.categoryOfProductRepository = categoryOfProductRepository;
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategoryOfProducts().stream().map(CategoryOfProduct::getName).toList());
    }

    public Product mapToProduct(ProductDto dto) {
        return new Product(dto.getName(),
                dto.getDescription(),
                dto.getCategories().stream().map(name ->
                        categoryOfProductRepository.findByName(name).orElse(null)).toList());
    }
}
