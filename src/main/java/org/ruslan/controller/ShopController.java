package org.ruslan.controller;


import lombok.extern.log4j.Log4j2;
import org.ruslan.core.dto.ShopDto;
import org.ruslan.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/shop")
public class ShopController {

    private ShopService shopService;


    @Autowired
    public ShopController(ShopService shopService)
    {
        this.shopService = shopService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopDto>> showAllShops() {
        log.info("Received GET request /shopAll");
        return ResponseEntity.ok(shopService.listShopDto());
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BookDto> showBook(@PathVariable Long id) {
//        log.info("Received GET request /books/" + id);
//        if (bookService.findById(id) == null) {
//            throw new NoSuchEntityException("There is no book with id=" + id + " in database");
//        }
//        return ResponseEntity.ok(bookService.findById(id));
//    }
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
