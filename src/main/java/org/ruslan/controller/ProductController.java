package org.ruslan.controller;


import lombok.extern.log4j.Log4j2;
import org.ruslan.core.dto.ProductDto;
import org.ruslan.services.ProductService;
import org.ruslan.web.webExceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {

    private  ProductService productService;


    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> showAllProducts() {
        log.info("Received GET request /product");
        return ResponseEntity.ok(productService.listProductDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> showProduct( @PathVariable(required = true, name="id") Long id) {
        log.info("Received GET request /books/" + id);
        if (productService.findById(id) == null) {
            throw new NoSuchEntityException("There is no book with id=" + id + " in database");
        }
        return ResponseEntity.ok(productService.findById(id));
    }
//
//    @PostMapping("/")
//    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto dto) {
//        log.info("Received POST request /books");
//        bookService.saveBook(dto);
//        return ResponseEntity.ok(dto);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<BookDto> updateBook(@RequestParam Long id, @RequestBody BookDto dto) {
//        log.info("Received Patch request /books/" + id);
//        bookService.saveBook(dto);
//        return ResponseEntity.ok(dto);
//    }
//
//    @PutMapping("/")
//    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto dto) {
//        log.info("Received PUT request /books");
//        bookService.saveBook(dto);
//        return ResponseEntity.ok(dto);
//    }
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
//    public ResponseEntity<BookDto> addBookToStock(@PathVariable Long id) {
//        log.info("Received PUT request /books/" + id + "/add-to-stock");
//        return ResponseEntity.ok(bookService.addBookToStockAndCancelRequestsForHttp(id));
//    }
//
//
//    @GetMapping("/poor-purchased")
//    public ResponseEntity<List<BookDto>> getStaleBooks() {
//        log.info("Received GET request /books/StaleBooks");
//        return ResponseEntity.ok(bookService.getStaleBooksDto());
//    }
}
