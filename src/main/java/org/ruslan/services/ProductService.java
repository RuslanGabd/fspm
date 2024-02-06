package org.ruslan.services;

import jakarta.transaction.Transactional;
import org.ruslan.core.dao.CategoryOfProductRepository;
import org.ruslan.core.dao.ProductPriceShopRepository;
import org.ruslan.core.dao.ProductRepository;
import org.ruslan.core.dao.ShopRepository;
import org.ruslan.core.dto.ProductDto;
import org.ruslan.core.entity.CategoryOfProduct;
import org.ruslan.core.entity.Product;
import org.ruslan.core.mapper.ProductMapper;
import org.ruslan.utils.csv.CsvReader;
import org.ruslan.utils.json.JsonReader;
import org.ruslan.utils.json.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ProductService {

    public String pathProductJSON = "src\\main\\resources\\Product.json";
    public String pathProductCSV = "src\\main\\resources\\Product.csv";
    private final ProductRepository productRepository;
    private final ProductPriceShopRepository productPriceShopRepository;
    private final ShopRepository shopRepository;
    private final CategoryOfProductRepository categoryOfProductRepository;
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();

    private final CsvReader csvReader = CsvReader.getInstance(pathProductCSV);

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductPriceShopRepository productPriceShopRepository, ShopRepository shopRepository, CategoryOfProductRepository categoryOfProductRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productPriceShopRepository = productPriceShopRepository;
        this.shopRepository = shopRepository;
        this.categoryOfProductRepository = categoryOfProductRepository;
        this.productMapper = productMapper;
    }

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

    public List<Product> getListSortedByAlphabet() {
        List<Product> sortedList = productRepository.findAll();
        sortedList.sort(Comparator.comparing(Product::getName));
        return sortedList;
    }

    public List<Product> getListProductsByCategory(String category) {
        List<Product> productsList = productRepository.findAll();
        CategoryOfProduct categoryOfProduct = categoryOfProductRepository.findByName(category).get();
        return productsList.stream().filter(product -> product.getCategoryOfProducts().contains(categoryOfProduct)).toList();
    }

    public List<Product> getListSortedByPrice() {
        List<Product> sortedList = productRepository.findAll();
        sortedList.sort(Comparator.comparing(Product::getName));
        return sortedList;

    }

    public List<ProductDto> listProductDto() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductDto)
                .collect(toList());
    }


    public void importProductsFromJsonToDataBase() {
        List<Product> productList = jsonReader.readEntities(Product.class, pathProductJSON);
        productList.forEach(product -> productRepository.save(product));
    }


    public void exportProductsToJson() {

        jsonWriter.writeEntities(productRepository.findAll(), pathProductJSON);
    }

    public ProductDto findById(Long id) {
        return productMapper.mapToProductDto(
                productRepository.findById(id)
                        .orElse(null));
    }

//    public List<Product> importProductFromCsvToDateBase() throws IOException {
//        List<Product> prList = new ArrayList<>();
//        Product pr = new Product();
//        for (int i = 1; i < csvReader.readCSV().size(); i++) {
//            List<String> valuesList = csvReader.readCSV().get(i);
//
//            String nameProduct = valuesList.get(0);
//            productRepository.findByName(nameProduct).ifPresentOrElse(
//                    (product) ->
//                    {
//                    },
//                    (){ pr.setName(nameProduct);
//                pr.setCategoryOfProducts((List<CategoryOfProduct>) categoryOfProductRepository.findByName(valuesList.get(1)).orElseGet(null));
//                        pr.setDescription(valuesList.get(2););
//                pr.setShop(shopRepository.findByName(valuesList.get(1)).orElseGet(null));
//                pr.setValuePrice(BigDecimal.valueOf(Long.parseLong(valuesList.get(2))));
//                pr.setDatePrice(LocalDate.parse(valuesList.get(3),formatter));
//                System.out.println(pps);
//                productPriceShopRepository.save(pps);
//                ppsList.add(pps);
//
//            }
//            );
//        }
//
//        return ppsList;
//    }


}
