package org.ruslan.services;

import jakarta.transaction.Transactional;
import org.ruslan.csv.CsvWriter;
import org.ruslan.dao.ProductRepository;
import org.ruslan.entity.Product;
import org.ruslan.json.JsonReader;
import org.ruslan.json.JsonWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    public String pathProductJSON = "src\\main\\resources\\Product.json";
    public String pathProductCSV = "src\\main\\resources\\Product.csv";
    private ProductRepository productRepository = ProductRepository.getInstance();
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();

    private final CsvWriter csvWriter = CsvWriter.getInstance(Product.class);
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long idProduct) {
        if (productRepository.findById(idProduct).isPresent()) {
            productRepository.delete(idProduct);
            return true;
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.update(product);
            return true;
        }
        return false;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getList() {
        return productRepository.findAll();
    }

    @Transactional
    public void importProductsFromJsonToDataBase() {
        List<Product> productList = jsonReader.readEntities(Product.class, pathProductJSON);
        productList.forEach(product -> productRepository.save(product));
    }

    @Transactional
    public void exportProductsToJson() {

        jsonWriter.writeEntities(productRepository.findAll(), pathProductJSON);
    }

    public void exportProductToCsv() throws Exception {
        csvWriter.toCsv(productRepository.findAll());
    }

}
