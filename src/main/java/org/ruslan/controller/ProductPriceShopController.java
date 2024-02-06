package org.ruslan.controller;


import lombok.extern.log4j.Log4j2;
import org.ruslan.core.dto.ProductPriceShopDto;
import org.ruslan.services.ProductPriceShopServices;
import org.ruslan.web.webExceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/pps")
public class ProductPriceShopController {

    private  ProductPriceShopServices productPriceShopServices;


    @Autowired
    public ProductPriceShopController(ProductPriceShopServices productPriceShopServices)
    {
        this.productPriceShopServices = productPriceShopServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductPriceShopDto>> showAllProductPriceShop() {
        log.info("Received GET request /AllProductPriceShop");
        return ResponseEntity.ok(productPriceShopServices.listProductPriceShopDto());
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductPriceShopDto> showProductPriceShop(@PathVariable Long id) {
        log.info("Received GET request /pps/" + id);
        if(productPriceShopServices.findById(id) == null) {
            throw new NoSuchEntityException("There is no book with id=" + id + " in database");
        }
        return ResponseEntity.ok(productPriceShopServices.findById(id));
    }
 
    @PutMapping("/")
    public ResponseEntity<ProductPriceShopDto> saveProductPriceShop(@RequestBody ProductPriceShopDto dto) {
        log.info("Received PUT request /books");
        productPriceShopServices.saveProductPriceShop(dto);
        return ResponseEntity.ok(dto);
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity <GenericResponseDto> deleteBook(@PathVariable Long id) {
//        log.info("Received DELETE request /books/" + id);
//        if (bookService.findById(id) == null) {
//            throw new NoSuchEntityException("There is no book with id=" + id + " in database");
//        } else {
//            bookService.deleteBook(id);
//            return  ResponseEntity.ok( new GenericResponseDto("Book with id=" + id + " was deleted"));
//        }
//    }
//
//    @PutMapping("/add-to-stock/{id}")
//    public ResponseEntity<ProductPriceShop> addBookToStock(@PathVariable Long id) {
//        log.info("Received PUT request /books/" + id + "/add-to-stock");
//        return ResponseEntity.ok(bookService.addBookToStockAndCancelRequestsForHttp(id));
//    }
//
//
//    @GetMapping("/poor-purchased")
//    public ResponseEntity<List<ProductPriceShop>> getStaleBooks() {
//        log.info("Received GET request /books/StaleBooks");
//        return ResponseEntity.ok(bookService.getStaleBooksDto());
//    }
}
