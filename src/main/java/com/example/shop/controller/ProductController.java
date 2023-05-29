package com.example.shop.controller;

import com.example.shop.entities.entity.Product;
import com.example.shop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v2/api/products",
        consumes = {"*/*"})

@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "12") int size,
                                        @RequestParam(required = false) String sort){
        return productService.findProducts(page-1, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Page<Product>> findProductsByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.findProductContainsName(name));
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

}
