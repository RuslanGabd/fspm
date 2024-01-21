package org.ruslan.services;

import org.ruslan.dao.ProductPriceShopRepository;
import org.ruslan.dao.ProductRepository;
import org.ruslan.json.JsonReader;
import org.ruslan.json.JsonWriter;

public class ProductPriceShopServices {

   ProductPriceShopRepository productPriceShopRepository =  ProductPriceShopRepository.getInstance();

    public String pathProductPriceShopJSON = "src\\main\\resources\\ProductPriceShop.json";
    private ProductRepository productRepository = ProductRepository.getInstance();
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();

    public void exportProductPriceShopToJson() {
       // List<ProductPriceShop> productPriceShops = productPriceShopRepository.findAll();
        jsonWriter.writeEntities(productPriceShopRepository.findAll(), pathProductPriceShopJSON);
    }
}
