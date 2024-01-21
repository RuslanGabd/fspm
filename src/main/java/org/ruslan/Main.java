package org.ruslan;

import org.ruslan.dao.ProductRepository;
import org.ruslan.services.ProductPriceShopServices;
import org.ruslan.services.ProductService;


public class Main {
    public static void main(String[] args) throws Exception {
// ProductRepository productRepository = new ProductRepository();
//
//      CategoryOfProductRepository categoryOfProductRepository = new CategoryOfProductRepository();
//ProductPriceShopRepository productPriceShopRepository = new ProductPriceShopRepository();
//
//        Optional<ProductPriceShop> productPriceShop =   productPriceShopRepository.findById(1L);
//        System.out.println(productPriceShop.map(ProductPriceShop::toString).orElse(null));
        ProductService productService = new ProductService();
      //  productService.saveProduct(new Product("beer",null,null,null));
       // System.out.println(productService.getProductById(1L));
        ProductRepository productRepository = new ProductRepository();
        //productService.exportProductToCsv();
        productService.exportProductToCsv();
        ProductPriceShopServices productPriceShopServices = new ProductPriceShopServices();
      // productPriceShopServices.exportProductPriceShopToJson();

      //categoryOfProductRepository.save(new CategoryOfProduct("FOOD2"));
        // productRepository.save("Bread","")
//
//        CategoryOfShopRepository categoryOfShopRepository = new CategoryOfShopRepository();
//        CategoryOfProduct categoryOfProduct1 =  new CategoryOfProduct("Food");
//        CategoryOfProduct categoryOfProduct2 =  new CategoryOfProduct("Food2");
//        categoryOfProductRepository.save(new CategoryOfProduct("MINI"));
//        ShopRepository shopRepository = new ShopRepository();
//
//         CategoryOfShop categoryOfShop1= new CategoryOfShop("BIG");
//
//          List<CategoryOfShop> categoryOfShopList = new ArrayList<> ();
//          categoryOfShopList.add(categoryOfShop1);
//        shopRepository.save(new Shop("Lidl", "4445, Boulvare", categoryOfShopList));
//
//        ProductPriceShopRepository productPriceShopRepository = new ProductPriceShopRepository();
//        productPriceShopRepository.save(new ProductPriceShop(null,));
//
//
//        productRepository.save(new Product("Bread", new ArrayList<int>(5,5,4)))
    }
}