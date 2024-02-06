package org.ruslan.services;

import jakarta.transaction.Transactional;
import org.ruslan.core.dao.ProductPriceShopRepository;
import org.ruslan.core.dao.ProductRepository;
import org.ruslan.core.dao.ShopRepository;
import org.ruslan.core.dto.ProductPriceShopDto;
import org.ruslan.core.entity.Product;
import org.ruslan.core.entity.ProductPriceShop;
import org.ruslan.core.mapper.ProductPriceShopMapper;
import org.ruslan.utils.csv.CsvReader;
import org.ruslan.utils.json.JsonReader;
import org.ruslan.utils.json.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductPriceShopServices {

    private final ProductPriceShopRepository productPriceShopRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    public String pathProductPriceShopJSON = "src\\main\\resources\\ProductPriceShop.json";
    public String pathProductPriceCSV = "src\\main\\resources\\Price.csv";
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();
    private final CsvReader csvReader = CsvReader.getInstance(pathProductPriceCSV);

    private final ProductPriceShopMapper productPriceShopMapper;

    @Autowired
    public ProductPriceShopServices(ProductPriceShopRepository productPriceShopRepository, ProductRepository productRepository, ShopRepository shopRepository, ProductPriceShopMapper productPriceShopMapper) {
        this.productPriceShopRepository = productPriceShopRepository;
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.productPriceShopMapper = productPriceShopMapper;
    }

    public List<ProductPriceShop> getAll() {
        return productPriceShopRepository.findAll();
    }

    public List<ProductPriceShopDto> listProductPriceShopDto() {
        System.out.println(productPriceShopRepository.findAll());
        return productPriceShopRepository.
                findAll().
                stream().
                map(productPriceShopMapper::mapToProductPriceShopDto).
                toList();
    }

    public void exportProductPriceShopToJson() {
        jsonWriter.writeEntities(productPriceShopRepository.findAll(), pathProductPriceShopJSON);
    }

    @Transactional
    public void importProductPriceShopFromJsonToDataBase() {
        List<Product> productList = jsonReader.readEntities(ProductPriceShop.class, pathProductPriceShopJSON);
        productList.forEach(product -> productRepository.save(product));
    }

    public List<ProductPriceShop> importProductPriceShopFromCsvToDateBase() throws IOException {
        List<ProductPriceShop> ppsList = new ArrayList<>();
        ProductPriceShop pps = new ProductPriceShop();
        for (int i = 1; i < csvReader.readCSV().size(); i++) {
            List<String> valuesList = csvReader.readCSV().get(i);
            String nameProduct = valuesList.get(0);
            productRepository.findByName(nameProduct).ifPresent(
                    (product) ->
                    {
                        pps.setProduct(product);
                        pps.setShop(shopRepository.findByName(valuesList.get(1)).orElseGet(null));
                        pps.setValuePrice(BigDecimal.valueOf(Long.valueOf(valuesList.get(2))));
                        pps.setDatePrice(LocalDate.parse(valuesList.get(3), formatter));
                        System.out.println(pps);
                        productPriceShopRepository.save(pps);
                        ppsList.add(pps);
                    }
            );
        }

        return ppsList;
    }

    public ProductPriceShopDto findById(Long id) {
        return productPriceShopMapper
                .mapToProductPriceShopDto(Objects.requireNonNull(productPriceShopRepository
                        .findById(id)
                        .orElse(null)));
    }

    public ProductPriceShop saveProductPriceShop(ProductPriceShopDto dto) {
    return     productPriceShopRepository.save(productPriceShopMapper.mapToProductPriceShop(dto));
    }
}
